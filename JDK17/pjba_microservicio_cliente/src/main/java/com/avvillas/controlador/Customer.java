package com.avvillas.controlador;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data // Lombok genera los getter y setter

public class Customer extends PanacheEntity { // PanacheEntity es una clase que nos permite hacer operaciones CRUD

    //Ahora vamos a definir los campos de la tabla
    private String code;
    private String accountNumber;
    private String names;
    private String surname;
    private String phone;
    private String address;
    //Ahora vamos a definir la relación con la tabla Product
    @OneToMany(mappedBy = "customer",cascade = {CascadeType.ALL},fetch = FetchType.EAGER) // un cliente puede tener varios productos
    @JsonManagedReference
    private List<Product> products; // lista de productos que tiene el cliente

    //Ahora vamos a definir el metodo toString, el cual nos permite imprimir los datos de un cliente
    @Override
    public String toString() {
        return "Customer{" +
                "code='" + code + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", names='" + names + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
