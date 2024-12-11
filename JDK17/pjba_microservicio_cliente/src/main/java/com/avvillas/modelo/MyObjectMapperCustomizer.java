package com.avvillas.modelo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.jackson.ObjectMapperCustomizer;
import jakarta.inject.Singleton;

@Singleton
public class MyObjectMapperCustomizer implements ObjectMapperCustomizer {

    @Override
    public void customize(ObjectMapper objectMapper) {
        // permite suprimir la serialización de propiedades con valores nulos, asi no se envian al cliente
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
