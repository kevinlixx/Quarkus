package org.microservicios.resteasyjackson;

import org.microservicios.entites.Product;
import org.microservicios.repositories.ProductRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductApi {
    @Inject
    ProductRepository pr;

    @GET
    public List<Product> list() {
        Instant start = Instant.now();
        List<Product> products = pr.listProduct();
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time (list): " + timeElapsed.toMillis() + " ms");
        return products;
    }

    @GET
    @Path("/{Id}")
    public Product getById(@PathParam("Id") Long Id) {
        Instant start = Instant.now();
        Product product = pr.findProduct(Id);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time (getById): " + timeElapsed.toMillis() + " ms");
        return product;
    }

    @POST
    public Response add(Product p) {
        Instant start = Instant.now();
        pr.createdProduct(p);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time (add): " + timeElapsed.toMillis() + " ms");
        return Response.ok().build();
    }

    @DELETE
    @Path("/{Id}")
    public Response delete(@PathParam("Id") Long Id) {
        Instant start = Instant.now();
        pr.deleteProduct(pr.findProduct(Id));
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time (delete): " + timeElapsed.toMillis() + " ms");
        return Response.ok().build();
    }

    @PUT
    @Path("/{Id}")
    public Response update(@PathParam("Id") Long Id, Product p) {
        Instant start = Instant.now();
        Product product = pr.findProduct(Id);
        product.setCode(p.getCode());
        product.setName(p.getName());
        product.setDescription(p.getDescription());
        pr.updateProduct(product);
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Execution time (update): " + timeElapsed.toMillis() + " ms");
        return Response.ok().build();
    }
}