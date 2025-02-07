package com.avvillas.modelo;

import com.avvillas.controlador.Product;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.List;

import static io.smallrye.mutiny.helpers.spies.Spy.onFailure;
import static io.smallrye.mutiny.helpers.spies.Spy.onItem;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.NOT_FOUND;

@Slf4j // para poder usar el log
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)

public class ClsProductApi {

    @GET
    public Uni<List<PanacheEntityBase>> list() {
        return Product.listAll(Sort.by("name"))
                .onItem().invoke(products -> log.info("Productos recuperados: " + products))
                .onFailure().invoke(e -> log.error("Error al recuperar productos", e));
    }



    @GET
    @Path("/{Id}")
    public Uni<PanacheEntityBase> getById(@PathParam("Id") Long Id) {
        return Product.findById(Id)
                .onItem().ifNotNull().invoke(product -> log.info("Producto recuperado: " + product))
                .onItem().ifNull().failWith(() -> new WebApplicationException("Producto no encontrado", NOT_FOUND))
                .onFailure().invoke(e -> log.error("Error al recuperar producto", e));
    }

    @POST
    public Uni<Response> add(Product p) {
        if (p == null) {
            log.error("Producto es nulo");
            return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).entity("Producto no puede ser nulo").build());
        }
        log.info("Agregando producto: " + p);
        return Panache.withTransaction(p::persist)

                .onItem().transform(inserted -> Response.status(Response.Status.CREATED).build())
                .onFailure().invoke(e -> log.error("Error al persistir producto", e));
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
                .onItem().ifNull().continueWith(Response.status(NOT_FOUND)::build)
                .onFailure().invoke(e -> log.error("Error al actualizar producto", e));
    }

    @DELETE
    @Path("/{Id}")
    public Uni<Response> delete(@PathParam("Id") Long Id) {
        return Panache.withTransaction(() -> Product.deleteById(Id))
            .onItem().transform(deleted -> deleted
                ? Response.ok().build()
                : Response.status(Response.Status.NOT_FOUND).build())
                .onFailure().invoke(e -> log.error("Error al eliminar producto", e));
    }



}




