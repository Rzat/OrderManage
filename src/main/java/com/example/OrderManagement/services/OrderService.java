package com.example.OrderManagement.services;

import com.example.OrderManagement.domain.Orders;

import java.util.List;

public interface OrderService {

   // OrdersDTO getCustomeById(Long id);

   // OrdersDTO findByCustomerId(Long id);

    List<Orders> findByCustomerId(Long id);

}
