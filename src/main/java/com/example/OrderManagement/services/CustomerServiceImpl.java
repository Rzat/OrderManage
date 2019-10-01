package com.example.OrderManagement.services;

import com.example.OrderManagement.api.v1.mapper.CustomerMapper;
import com.example.OrderManagement.api.v1.mapper.OrdersMapper;
import com.example.OrderManagement.api.v1.model.CustomerDTO;
import com.example.OrderManagement.api.v1.model.OrdersDTO;
import com.example.OrderManagement.domain.Customer;
import com.example.OrderManagement.domain.Orders;
import com.example.OrderManagement.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final OrdersMapper ordersMapper;

    public CustomerServiceImpl(CustomerMapper mapper, CustomerRepository customerRepository, OrdersMapper ordersMapper) {
        this.customerMapper = mapper;
        this.customerRepository = customerRepository;
        this.ordersMapper = ordersMapper;
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

        return returnDto;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public OrdersDTO saveOrders(OrdersDTO ordersDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(ordersDTO.getCustomerId());

        if (!customerOptional.isPresent()) {
            log.error("Customer not found for id: " + ordersDTO.getId());
            return new OrdersDTO();
        }
        Customer customer = customerOptional.get();
        Optional<Orders> ordersOptional = customer
                .getOrders()
                .stream()
                .filter(orders -> orders.getId().equals(ordersDTO.getId()))
                .findFirst();
        if (ordersOptional.isPresent()) {
            Orders orderFound = ordersOptional.get();
            orderFound.setDescription(ordersDTO.getDescription());
        } else {
            //add new order
            Orders orders = ordersMapper.ordersDtoToOrders(ordersDTO);
            orders.setCustomer(customer);
            customer.addOrder(orders);
        }

        Customer savedCustomer = customerRepository.save(customer);

        Optional<Orders> savedOrdersOptional = savedCustomer.getOrders().stream()
                .filter(orders -> orders.getId().equals(ordersDTO.getId()))
                .findFirst();


        if (!savedOrdersOptional.isPresent()) {
            savedOrdersOptional = savedCustomer.getOrders().stream()
                    .filter(orders -> orders.getDescription().equals(ordersDTO.getDescription()))
                    .findFirst();
        }


        return ordersMapper.ordersToOrdersDto(savedOrdersOptional.get());
    }

    @Override
    public CustomerDTO findCommandById(Long id) {
        return customerMapper.customerToCustomerDTO(findById(id));
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()) {
            throw new RuntimeException("Customer Not Found");
        }
        return customerOptional.get();

    }


}
