package org.microservicios.resteasyjackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.microservicios.entites.Customer;
import org.microservicios.entites.Product;
import org.microservicios.repositories.CustomerRepository;
import org.jboss.resteasy.reactive.RestPath;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static jakarta.ws.rs.core.Response.Status.*;

@Slf4j
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CustomerApi {
 @Inject
 CustomerRepository cr;
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



    @GET
    public Uni<List<PanacheEntityBase>> list() {
        return Customer.listAll(Sort.by("names"));
    }


    @GET
    @Path("using-repository")
    public Uni<List<Customer>> listUsingRepository() {
        return cr.findAll().list();
    }

    @GET
    @Path("/{Id}")
    public Uni<PanacheEntityBase> getById(@PathParam("Id") Long Id) {
        log.info("Fetching customer with ID: " + Id);
        return Customer.findById(Id);
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
                    errorCustomer.setNames("Error fetching customer or products: " + e.getMessage());
                    return errorCustomer;
                });
    }

    @POST
    public Uni<Response> add(Customer c) {
        if (c == null) {
            log.error("Customer is null");
            return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).entity("Customer cannot be null").build());
        }
        log.info("Adding customer: " + c);
        return Panache.withTransaction(c::persist)
                .onFailure().invoke(e -> log.error("Error persisting customer", e))
                .replaceWith(Response.status(Response.Status.CREATED)::build);
    }

    @DELETE
    @Path("/{Id}")
    public Uni<Response> delete(@PathParam("Id") Long Id) {
        return Panache.withTransaction(() -> Customer.deleteById(Id))
                .map(deleted -> deleted
                        ? Response.ok().status(NO_CONTENT).build()
                        : Response.ok().status(NOT_FOUND).build());
    }
    @PUT
    @Path("{id}")
    public Uni<Response> update(@RestPath Long id, Customer c) {
        if (c == null || c.getCode() == null) {
            throw new WebApplicationException("Product code was not set on request.", HttpResponseStatus.UNPROCESSABLE_ENTITY.code());
        }
        return Panache
                .withTransaction(() -> Customer.<Customer> findById(id)
                        .onItem().ifNotNull().invoke(entity -> {
                            entity.setNames(c.getNames());
                            entity.setAccountNumber(c.getAccountNumber());
                            entity.setCode(c.getCode());
                        })
                )
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }
    @GET
    @Path("/products")
    public Uni<List<Product>> getAllProducts(){
        return webClient.get(8081, "product", "/product").send()
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
