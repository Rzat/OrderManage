package com.example.OrderManagement.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CustomerDTO {


    private Long id;
    private String firstName;
    private String lastName;
    private String category="Regular";
    private int discountInPercent;
    private int totalDiscountGiven;
   // private Set<Orders> orders = new HashSet<>();
}
