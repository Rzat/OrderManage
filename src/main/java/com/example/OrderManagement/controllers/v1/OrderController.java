package com.example.OrderManagement.controllers.v1;


import com.example.OrderManagement.domain.Orders;
import com.example.OrderManagement.repositories.OrderRepositories;
import com.example.OrderManagement.services.CustomerService;
import com.example.OrderManagement.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final OrderRepositories orderRepositories;
    private final CustomerService customerService;

    public OrderController(OrderService orderService, OrderRepositories orderRepositories, CustomerService customerService) {
        this.orderService = orderService;
        this.orderRepositories = orderRepositories;
        this.customerService = customerService;
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Orders>> getAllOrdersByCustomerById(@PathVariable Long id) {
        List<Orders> ordersDTO = orderService.findByCustomerId(id);
        ordersDTO.forEach((n) -> System.out.println("***" + n.getDescription()));
        if(ordersDTO.isEmpty()){
            return new ResponseEntity<List<Orders>>(ordersDTO, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Orders>>(ordersDTO, HttpStatus.OK);
    }

}
