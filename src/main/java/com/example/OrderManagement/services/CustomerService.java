package com.example.OrderManagement.services;

import com.example.OrderManagement.api.v1.model.CustomerDTO;
import com.example.OrderManagement.api.v1.model.OrdersDTO;

public interface CustomerService {
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Long id);

    OrdersDTO saveOrders(OrdersDTO ordersDTO);
}
