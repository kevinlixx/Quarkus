package org.microservicios.entites;


import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*; //


@Entity
@Data

public class Product extends PanacheEntity {
    private String code;
    private String name;
    private String description;
}
