package com.avvillas.controlador;


import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Data;


@Entity
@Data

public class Product extends PanacheEntity {
    private String code;
    private String name;
    private String description;
}
