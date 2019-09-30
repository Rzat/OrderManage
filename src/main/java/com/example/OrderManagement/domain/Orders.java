package com.example.OrderManagement.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"customer"})
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    private Customer customer;

    public Orders() {

    }

    public Orders(String description, Customer customer) {
        this.description = description;
        this.customer = customer;
    }
}
