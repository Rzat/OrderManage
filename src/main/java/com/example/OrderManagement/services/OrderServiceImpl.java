package com.example.OrderManagement.services;

import com.example.OrderManagement.api.v1.mapper.OrdersMapper;
import com.example.OrderManagement.api.v1.model.OrdersDTO;
import com.example.OrderManagement.domain.Orders;
import com.example.OrderManagement.repositories.OrderRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepositories orderRepositories;
    private final OrdersMapper ordersMapper;

    public OrderServiceImpl(OrderRepositories orderRepositories, OrdersMapper ordersMapper) {
        this.orderRepositories = orderRepositories;
        this.ordersMapper = ordersMapper;
    }

    @Override
    public List<Orders> findByCustomerId(Long id) {
        List<Orders> ordersDTO=orderRepositories.findByCustomer_Id(id);
        return ordersDTO;
    }

    //  @Override
    public OrdersDTO getCustomeById(Long id) {
      /*
        Optional<Orders> orders = orderRepositories.findByCustomer_Id(id);

        return orderRepositories.findAllByCustomer_Id(id)
                .map(ordersMapper::ordersToOrdersDto)
                .orElseThrow(ResourceNotFoundException::new);
        */
        return null;
    }


 /*   @Override
    public OrdersDTO findByCustomerId(Long id) {
        return ordersMapper.ordersToOrdersDto(findByCustId(id));
    }
*/
/*
    public Orders findByCustId(Long id) {
        // Optional<Orders> ordersOptional=orderRepositories.findByCustomer_Id(id);
        Optional<Orders> ordersOptional=orderRepositories.findByCustomer_Id(id);

        if(!ordersOptional.isPresent()){
            throw  new RuntimeException("Orders Not found");
        }
        return ordersOptional.get();
        List<Orders> ordersList = orderRepositories.findByCustomer_Id(id);
        return null;
    }*/


}
