package com.example.OrderManagement.services;

import com.example.OrderManagement.api.v1.mapper.CustomerMapper;
import com.example.OrderManagement.api.v1.mapper.OrdersMapper;
import com.example.OrderManagement.api.v1.model.CustomerDTO;
import com.example.OrderManagement.api.v1.model.OrdersDTO;
import com.example.OrderManagement.domain.Customer;
import com.example.OrderManagement.domain.Orders;
import com.example.OrderManagement.repositories.CustomerRepository;
import com.example.OrderManagement.repositories.OrderRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final OrdersMapper ordersMapper;
    private final OrderRepositories orderRepositories;

    public CustomerServiceImpl(CustomerMapper mapper, CustomerRepository customerRepository, OrdersMapper ordersMapper, OrderRepositories orderRepositories) {
        this.customerMapper = mapper;
        this.customerRepository = customerRepository;
        this.ordersMapper = ordersMapper;
        this.orderRepositories = orderRepositories;
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
            List<Orders> ordersList = orderRepositories.findByCustomer_Id(ordersDTO.getCustomerId());
            System.out.println("Size of Order List" + ordersList.size());
            int size = ordersList.size();
            if (size == 8) {
                sendCustomerUpgradationNotificationMail("\"You have placed 9 orders with us. Buy one more stuff and you will be promoted\n" +
                        "to Gold customer and enjoy 10% discounts!\"");
            } else if (size == 18) {
                sendCustomerUpgradationNotificationMail("\"You have placed 19 orders with us. Buy one more stuff and you will be promoted\n" +
                        "to Platinum customer and enjoy 20% discounts!\"");
            }
            if (size > 18) {
                customer.setCategory("Platinum");
                customer.setDiscount("20%");
                log.info("CustomerCategory: " + customer.getCategory());
            } else if (size > 8) {
                customer.setCategory("Gold");
                customer.setDiscount("10%");
                log.info("CustomerCategory: " + customer.getCategory());
            }

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

    private void sendCustomerUpgradationNotificationMail(String s) {
        log.info(s);
        log.info("Sent an email to Customers");
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

    @Override
    public OrdersDTO findByCustomerIdAndOrderId(Long customerId, Long orderId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()) {
            log.error("customer i not found: " + customerId);
        }
        Customer customer = customerOptional.get();

        Optional<OrdersDTO> ordersDTO = customer.getOrders().stream()
                .filter(orders -> orders.getId().equals(orderId))
                .map(orders -> ordersMapper.ordersToOrdersDto(orders)).findFirst();

        if (!ordersDTO.isPresent()) {
            log.error("Order id not found: " + orderId);
        }
        return ordersDTO.get();
    }


}
