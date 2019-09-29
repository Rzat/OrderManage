package com.example.OrderManagement.services;

import com.example.OrderManagement.api.v1.mapper.CustomerMapper;
import com.example.OrderManagement.api.v1.model.CustomerDTO;
import com.example.OrderManagement.domain.Customer;
import com.example.OrderManagement.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper mapper, CustomerRepository customerRepository) {
        this.customerMapper = mapper;
        this.customerRepository = customerRepository;
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
}
