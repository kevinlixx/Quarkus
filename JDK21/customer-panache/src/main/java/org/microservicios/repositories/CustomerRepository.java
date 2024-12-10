package org.microservicios.repositories;


import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import org.microservicios.entites.Customer;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepositoryBase<Customer,Long> {
}
