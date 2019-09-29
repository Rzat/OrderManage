package com.example.OrderManagement.repositories;

import com.example.OrderManagement.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
