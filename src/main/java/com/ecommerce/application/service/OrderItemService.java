package com.ecommerce.application.service;

import com.ecommerce.application.dto.OrderItemDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderItemService {

    OrderItemDto createOrderItem(OrderItemDto orderItemDto);
    Optional<OrderItemDto> getOrderItemById(UUID orderItemId);
    List<OrderItemDto> getAllOrderItems();
    OrderItemDto updateOrderItem(UUID orderItemId, OrderItemDto orderItemDto);
    void deleteOrderItem(UUID orderItemId);
}
