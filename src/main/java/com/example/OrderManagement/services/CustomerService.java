package com.example.OrderManagement.services;

import com.example.OrderManagement.api.v1.model.CustomerDTO;

public interface CustomerService {
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
}
