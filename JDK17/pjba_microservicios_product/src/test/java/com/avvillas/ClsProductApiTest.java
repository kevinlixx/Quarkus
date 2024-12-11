package com.avvillas;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;

@QuarkusTest
class ClsProductApiTest {
    @Test
    void testProductEndpoint() {
        given()
          .when().get("/product")
          .then()
             .statusCode(200)
             .body("$.size()", greaterThan(0)); // Verifica que la respuesta tenga más de un elemento
    }

}