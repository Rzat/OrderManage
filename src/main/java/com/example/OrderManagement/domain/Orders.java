package com.example.OrderManagement.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@EqualsAndHashCode(exclude = {"customer"})
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @JsonIgnore
    @ManyToOne
    private Customer customer;
    private int discountAppliedInPercent;

    public Orders() {

    }

    public Orders(String description, Customer customer) {
        this.description = description;
        this.customer = customer;
    }
}
