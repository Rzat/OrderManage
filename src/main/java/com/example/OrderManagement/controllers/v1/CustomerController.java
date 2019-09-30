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
      //  OrdersDTO dto = customerService.saveOrders(ordersDTO);
        System.out.println(ordersDTO.getCustomerId());
        return new ResponseEntity<OrdersDTO>(customerService.saveOrders(ordersDTO), HttpStatus.OK);
    }

/*
    @PostMapping("/customer/{customerId}/order")
    public String saveOrder(@RequestBody OrdersDTO ordersDTO, @PathVariable Long customerId) {

        System.out.println(ordersDTO.getCustomerId());
        OrdersDTO dto = customerService.saveOrders(ordersDTO);
        log.debug("Logger of saved Order" + dto.getDescription());
        return "order saved";
    }*/
}
