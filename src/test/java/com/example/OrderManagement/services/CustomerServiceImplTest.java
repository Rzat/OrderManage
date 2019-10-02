package com.example.OrderManagement.services;

import com.example.OrderManagement.api.v1.mapper.CustomerMapper;
import com.example.OrderManagement.api.v1.mapper.OrdersMapper;
import com.example.OrderManagement.api.v1.model.CustomerDTO;
import com.example.OrderManagement.api.v1.model.OrdersDTO;
import com.example.OrderManagement.domain.Customer;
import com.example.OrderManagement.domain.Orders;
import com.example.OrderManagement.repositories.CustomerRepository;
import com.example.OrderManagement.repositories.OrderRepositories;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;
    @Mock
    OrderRepositories orderRepositories;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    OrdersMapper ordersMapper = OrdersMapper.INSTANCE;

    CustomerServiceImpl customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerMapper, customerRepository, ordersMapper, orderRepositories);
    }

    @Test
    public void createNewCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Cello");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
    }

    @Test
    public void testSaveRecipeCommand() throws Exception {
        //given
        OrdersDTO command = new OrdersDTO();
        command.setId(3L);
        command.setCustomerId(2L);

        Optional<Customer> recipeOptional = Optional.of(new Customer());

        Customer savedRecipe = new Customer();
        savedRecipe.addOrder(new Orders());
        savedRecipe.getOrders().iterator().next().setId(3L);


        when(customerRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(customerRepository.save(any())).thenReturn(savedRecipe);

        //when
        OrdersDTO savedCommand = customerService.saveOrders(command);

        //then
        assertEquals(Long.valueOf(3L), savedCommand.getId());
        //verify(recipeRepository, times(1)).findById(anyLong());
        //verify(recipeRepository, times(1)).save(any(Customer.class));

    }
}