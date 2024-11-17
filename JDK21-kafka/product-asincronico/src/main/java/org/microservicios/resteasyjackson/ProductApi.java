package org.microservicios.resteasyjackson;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import org.microservicios.entites.Product;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.microservicios.kafka.producer.ProductProducer;

import java.util.List;

import static io.quarkus.hibernate.reactive.panache.PanacheEntityBase.findById;
import static io.smallrye.mutiny.helpers.spies.Spy.onItem;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;


@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductApi {

    @Inject
    ProductProducer productProducer;

    @GET
    public Uni<List<Product>> list() {
        long startTime = System.nanoTime();
        return Product.<Product>listAll()
                .invoke(products -> {
                    long endTime = System.nanoTime();
                    long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
                    System.out.println("Execution time: " + duration + " ms");
                });
    }

    @GET
    @Path("/{Id}")
    public Uni<PanacheEntity> getById(@PathParam("Id") Long Id) {
        return Product.findById(Id);
    }

    @POST
    public Uni<Response> add(Product p) {
        return Panache.withTransaction(p::persist)
                .replaceWith(Response.status(Response.Status.CREATED).build());
    }

    @DELETE
    @Path("/{Id}")
    public Uni<Response> delete(@PathParam("Id") Long Id) {
        return Panache.withTransaction(() -> Product.deleteById(Id))
                .map(deleted -> deleted
                        ? Response.ok().build()
                        : Response.status(Response.Status.NOT_FOUND).build());
    }

    @PUT
    @Path("/{Id}")
    public Uni<Response> update(@PathParam("Id") Long Id, Product p) {
        return Panache
                .withTransaction(() -> Product.<Product>findById(Id)
                        .onItem().ifNotNull().invoke(entity -> {
                            entity.setCode(p.getCode());
                            entity.setName(p.getName());
                            entity.setDescription(p.getDescription());
                        })
                )
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.status(NOT_FOUND)::build);
    }

    @POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> sendProduct(Product product) {
        productProducer.sendProduct(product);
        return Uni.createFrom().item(Response.ok().build());
    }

}
