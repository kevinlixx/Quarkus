package com.avvillas.modelo;

import com.avvillas.controlador.Customer;
import com.avvillas.controlador.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;



import java.util.ArrayList;
import java.util.List;

import static io.smallrye.mutiny.helpers.spies.Spy.onFailure;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NO_CONTENT;


@Slf4j
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClsCustomerApi {
    @Inject
    Vertx vertx;

    @ConfigProperty(name = "product.url")
    String productServiceUrl;

    private WebClient webClient;

    @PostConstruct
    void initialize() {
        this.webClient = WebClient.create(vertx,
                new WebClientOptions().setDefaultHost(productServiceUrl)
                        .setSsl(false).setTrustAll(true));
    }


    // busca todos los registros de la tabla el names es el campo por el cual se ordena
    @GET
    public Uni<List<PanacheEntityBase>> list() { // nos busca todos los registros de la tabla el names es el campo por el cual se ordena
        return Customer.listAll(Sort.by("names")) // se ordena por el campo name
                .onItem().invoke(customers -> log.info("Retrieved customers: " + customers)) // si es exitoso se imprime el resultado
                .onFailure().invoke(e -> log.error("Error retrieving customers", e)); // si hay un error se imprime el error
    }

    // busca un registro por el id
    @GET
    @Path("/{Id}")
    public Uni<PanacheEntityBase> getById(@PathParam("Id") Long Id) { // busca un registro por el id
        return Customer.findById(Id) // busca un registro por el id
                .onItem().ifNotNull().invoke(customer -> log.info("Retrieved customer: " + customer)) // si es exitoso se imprime el resultado
                .onItem().ifNull().failWith(() -> new WebApplicationException("Customer not found", NOT_FOUND)) // si es nulo se imprime un mensaje de error
                .onFailure().invoke(e -> log.error("Error retrieving customer", e)); // si hay un error se imprime el error
    }

    // agrega un registro
    @POST
    public Uni<Response> add(Customer c) { // agrega un registro
        if (c == null) { // si el registro es nulo
            log.error("Customer is null");
            return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).entity("Customer cannot be null").build()); // nos indica que el registro no puede ser nulo
        }
        log.info("Adding customer: " + c); // si no es nulo se imprime el registro
        return Panache.withTransaction(c::persist) // se persiste el registro, los :: son para referenciar un metodo en este caso persist
                .onItem().transform(inserted -> Response.status(Response.Status.CREATED).build()) // si es exitoso indica que se creo el registro
                .onFailure().invoke(e -> log.error("Error persisting customer", e)); // si hay un error se imprime el error
    }


    // Actualiza un registro
    @PUT
    @Path("/{Id}")
    public Uni<Response> update(@PathParam("Id") Long Id, Customer c) { // Actualiza un registro
        return Panache
                .withTransaction(() -> Customer.<Customer>findById(Id) // busca el registro por el id
                        .onItem().ifNotNull().invoke(customer -> { // si es diferente de nulo
                            customer.setAccountNumber(c.getAccountNumber()); // se actualiza el campo accountNumber
                            customer.setNames(c.getNames()); // se actualiza los campos
                            customer.setSurname(c.getSurname());
                            customer.setPhone(c.getPhone());
                            customer.setAddress(c.getAddress());
                            customer.setProducts(c.getProducts());
                        })
                )
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.status(NOT_FOUND)::build)
                .onFailure().invoke(e -> log.error("Error updating customer", e)); // se imprime el error
    }

    // Elimina un registro
    @DELETE
    @Path("/{Id}")
    public Uni<Response> delete(@PathParam("Id") Long Id) { // Elimina un registro
        return Panache.withTransaction(() -> Customer.deleteById(Id)) // se elimina el registro por el id
                .onItem().transform(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.status(NOT_FOUND).build())
                // el ? es un operador ternario si es verdadero se imprime el mensaje de que se elimino el registro
                // el : es un operador ternario si es falso se imprime el mensaje de que no se encontro el registro
                .onFailure().invoke(e -> log.error("Error deleting customer", e)); // si hay un error se imprime el error
    }
    @GET
    @Path("/{Id}/product")
    public Uni<Customer> getByIdProduct(@PathParam("Id") Long Id) {
        log.info("Fetching customer with ID: " + Id);
        return Uni.combine().all().unis(getCustomerReactive(Id), getAllProducts())
                .asTuple()
                .map(tuple -> {
                    Customer customer = tuple.getItem1();
                    List<Product> products = tuple.getItem2();
                    log.info("Customer: " + customer);
                    log.info("Products: " + products);
                    customer.getProducts().forEach(product -> {
                        products.forEach(p -> {
                            log.info("Ids are: " + product.getProduct() + " = " + p.getId());
                            if (product.getProduct().equals(p.getId())) {
                                product.setName(p.getName());
                                product.setDescription(p.getDescription());
                            }
                        });
                    });
                    return customer;
                })
                .onFailure().retry().atMost(3) // Retry up to 3 times on failure
                .onFailure().invoke(e -> log.error("Error fetching customer or products", e))
                .onFailure().recoverWithItem(e -> {
                    log.error("Recovering from error: ", e);
                    Customer errorCustomer = new Customer();
                    errorCustomer.setNames("Error fetching: " + e.getMessage());
                    return errorCustomer;
                });
    }


    @GET
    @Path("/products")
    public Uni<List<Product>> getAllProducts(){
        return webClient.get(8081, "localhost", "/product").send()
                .onFailure().invoke(res -> log.error("Error recuperando productos ", res))
                .onItem().transform(res -> {
                    List<Product> lista = new ArrayList<>();
                    JsonArray objects = res.bodyAsJsonArray();
                    objects.forEach(p -> {
                        log.info("See Objects: " + objects);
                        ObjectMapper objectMapper = new ObjectMapper();
                        // Pass JSON string and the POJO class
                        Product product = null;
                        try {
                            product = objectMapper.readValue(p.toString(), Product.class);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                        lista.add(product);
                    });
                    return lista;
                });
    }

    private Uni<Customer> getCustomerReactive(Long Id){
        return Customer.findById(Id);
    }

}