package com.avvillas.controlador;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Lombok genera los getter y setter
public class Product extends PanacheEntity { // PanacheEntity es una clase que nos permite hacer operaciones CRUD

    @Transient
    private Long id;

    // muchos productos pueden pertenecer a un cliente
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id") // el nombre de la columna en la tabla producto y el nombre de la columna en la tabla cliente
    @JsonBackReference
    private Customer customer;
    @Column
    private Long product;

    // almacenaran los datos del producto de manera temporal
    @Transient
    private String name;
    @Transient
    private String code;
    @Transient
    private String description;
}
