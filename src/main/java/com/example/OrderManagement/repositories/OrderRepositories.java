package com.example.OrderManagement.repositories;

import com.example.OrderManagement.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepositories extends JpaRepository<Orders, Long> {

   // Optional<Orders> findByCustomer_Id(long id);
    Optional<Orders> findAllByCustomer_Id(long id);
    List<Orders> findByCustomer_Id(Long id);
}
