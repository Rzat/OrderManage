
package com.example.OrderManagement.bootstrap;


import com.example.OrderManagement.domain.Customer;
import com.example.OrderManagement.domain.Orders;
import com.example.OrderManagement.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CustomerBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CustomerRepository customerRepository;

    public CustomerBootstrap(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        customerRepository.saveAll(getCustomer());
    }

    private List<Customer> getCustomer() {
        List<Customer> customers = new ArrayList<>(2);

        Customer firstCustomer = new Customer();
        firstCustomer.setFirstName("Roman");
        firstCustomer.setLastName("Reigns");
        firstCustomer.getOrders().add(new Orders("FirstOrder", firstCustomer));
        customers.add(firstCustomer);

        Customer secondCustomer = new Customer();
        secondCustomer.setFirstName("Seth");
        secondCustomer.setLastName("Rollins");
        secondCustomer.getOrders().add(new Orders("SecondOrder", secondCustomer));
        customers.add(secondCustomer);

        log.info("Customers added on startup");
        return customers;
    }
}
