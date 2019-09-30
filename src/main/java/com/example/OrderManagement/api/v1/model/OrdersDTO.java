package com.example.OrderManagement.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    private Long id;
    private Long customerId;
    private String description;
}
