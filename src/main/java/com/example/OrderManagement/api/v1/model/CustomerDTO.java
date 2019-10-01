package com.example.OrderManagement.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {


    private Long id;
    private String firstName;
    private String lastName;
    private String category="Regular";
   // private Set<Orders> orders = new HashSet<>();
}
