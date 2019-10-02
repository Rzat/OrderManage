package com.example.OrderManagement.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String category = "Regular";
    private int discountInPercent;
    private int totalDiscountGiven;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Orders> orders = new HashSet<>();

    public Customer addOrder(Orders orders) {
        orders.setCustomer(this);
        this.orders.add(orders);
        return this;
    }
}
