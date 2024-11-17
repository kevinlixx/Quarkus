package org.microservicios.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.microservicios.entites.Product;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped // permite que CDI cree una instancia de esta clase
public class ProductConsumer {

    private final List<Product> products = new ArrayList<>();

    @Incoming("entrada-productos") // indica que este mtodo es un consumidor de mensajese
    @Blocking
    public void consume (String productJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Product product = objectMapper.readValue(productJson, Product.class); // convierte el JSON en un objeto Product
            products.add(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Product> getProducts() {
        return products;
    }
}
