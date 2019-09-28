package com.example.OrderManagement.api.v1.mapper;

import com.example.OrderManagement.api.v1.model.CustomerDTO;
import com.example.OrderManagement.domain.Customer;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomerMapperTest {

    public static final String FIRSTNAME = "Ubuntu";
    public static final String LASTNAME = "Intel";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;


    @Test
    public void customerToCustomerDTO() {
        //given
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        //when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(FIRSTNAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
    }

    @Test
    public void customerDtoToCustomer() {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRSTNAME);
        customerDTO.setLastName(LASTNAME);

        //when
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);

        //then
       // assertEquals(FIRSTNAME, customer.getFirstName());
       // assertEquals(LASTNAME, customer.getLastName());

        assertThat(customer.getFirstName(),is(equalTo(FIRSTNAME)));
        assertThat(customer.getLastName(),is(equalTo(LASTNAME)));


    }
}