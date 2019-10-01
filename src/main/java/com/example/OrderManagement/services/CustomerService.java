package com.example.OrderManagement.services;

import com.example.OrderManagement.api.v1.model.CustomerDTO;
import com.example.OrderManagement.api.v1.model.OrdersDTO;
import com.example.OrderManagement.domain.Customer;

public interface CustomerService {
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Long id);

    OrdersDTO saveOrders(OrdersDTO ordersDTO);

    CustomerDTO findCommandById(Long id);

    Customer findById(Long id);

}
