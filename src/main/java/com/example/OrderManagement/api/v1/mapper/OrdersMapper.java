package com.example.OrderManagement.api.v1.mapper;

import com.example.OrderManagement.api.v1.model.OrdersDTO;
import com.example.OrderManagement.domain.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrdersMapper {

    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    OrdersDTO ordersToOrdersDto(Orders orders);

    Orders ordersDtoToOrders(OrdersDTO ordersDTO);
}
