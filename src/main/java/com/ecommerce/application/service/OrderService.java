package com.ecommerce.application.service;

import com.ecommerce.application.dto.OrderDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);
    Optional<OrderDto> getOrderById(UUID orderId);
    List<OrderDto> getAllOrders();
    OrderDto updateOrder(UUID orderId, OrderDto orderDto);
    void deleteOrder(UUID orderId);
    List<OrderDto> getOrderByUserId(UUID userId);
}
