package com.ecommerce.application.mapper;

import com.ecommerce.application.dto.OrderItemDto;
import com.ecommerce.application.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {
    public OrderItemDto toOrderItemDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrderItemId(orderItem.getOrderItemId());
        orderItemDto.setOrder(new OrderMapper().toOrderDto(orderItem.getOrder()));
        orderItemDto.setProduct(new ProductMapper().toProductDto(orderItem.getProduct()));
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());

        return orderItemDto;
    }

    public OrderItem toOrderItem(OrderItemDto orderItemDto) {
        if (orderItemDto == null) {
            return null;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(orderItemDto.getOrderItemId());
        orderItem.setOrder(new OrderMapper().toOrder(orderItemDto.getOrder()));
        orderItem.setProduct(new ProductMapper().toProduct(orderItemDto.getProduct()));
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setPrice(orderItemDto.getPrice());
        return orderItem;
    }
}
