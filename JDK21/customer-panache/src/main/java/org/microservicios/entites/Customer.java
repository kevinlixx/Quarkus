package org.microservicios.entites;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Data;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer  extends PanacheEntity {

    @SequenceGenerator(name = "customer_seq", sequenceName = "Customer_SEQ", allocationSize = 1)
    private String code;
    private String accountNumber;
    private String names;
    private String surname;
    private String phone;
    private String address;
    @OneToMany(mappedBy = "customer",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Product> products;

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
