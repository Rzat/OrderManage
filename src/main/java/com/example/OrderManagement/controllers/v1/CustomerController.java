package com.example.OrderManagement.controllers.v1;

import com.example.OrderManagement.api.v1.model.CustomerDTO;
import com.example.OrderManagement.api.v1.model.OrdersDTO;
import com.example.OrderManagement.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(CustomerController.BASE_URL)
@Slf4j
public class CustomerController {
    public static final String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomer(customerDTO),
                HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id), HttpStatus.OK);
    }


    @PostMapping("/customer/{customerId}/order")
    public ResponseEntity<OrdersDTO> saveOrder(@RequestBody OrdersDTO ordersDTO, @PathVariable Long customerId) {
        return new ResponseEntity<OrdersDTO>(customerService.saveOrders(ordersDTO), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<CustomerDTO> getCustomerByCustomerId(@PathVariable Long id) {
        return new ResponseEntity<CustomerDTO>(customerService.findCommandById(id), HttpStatus.OK);
    }


    @GetMapping("/{customerId}/ingredients/{orderId}/show")
    public ResponseEntity<Object> getSelectedOrderByCustomerIdOrderId(@PathVariable Long customerId, @PathVariable Long orderId) {
        return new ResponseEntity<Object>(customerService.findByCustomerIdAndOrderId(customerId, orderId), HttpStatus.OK);
    }


}
