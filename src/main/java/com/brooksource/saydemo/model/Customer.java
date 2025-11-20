package com.brooksource.saydemo.model;

import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    protected Customer() {}

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
