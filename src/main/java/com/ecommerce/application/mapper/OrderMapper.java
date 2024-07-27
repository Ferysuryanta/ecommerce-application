package com.ecommerce.application.mapper;

import com.ecommerce.application.dto.OrderDto;
import com.ecommerce.application.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setUser(new UserMapper().toUser(orderDto.getUser()));
        order.setOrderDate(orderDto.getOrderDate());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setStatus(orderDto.getStatus());
        order.setPayment(new PaymentMapper().toPayment(orderDto.getPayment()));
        order.setShipping(new ShippingMapper().toShipping(orderDto.getShipping()));

        if (orderDto.getOrderItems() != null) {
            order.setOrderItems(orderDto.getOrderItems()
                    .stream()
                    .map(new OrderItemMapper()::toOrderItem)
                    .toList());
        }
        return order;
    }

    public OrderDto toOrderDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setUser(new UserMapper().toUserDto(order.getUser()));
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setStatus(order.getStatus());
        orderDto.setPayment(new PaymentMapper().toPaymentDto(order.getPayment()));
        orderDto.setShipping(new ShippingMapper().toShippingDto(order.getShipping()));

        if (order.getOrderItems() != null) {
            orderDto.setOrderItems(order.getOrderItems()
                    .stream()
                    .map(new OrderItemMapper()::toOrderItemDto)
                    .toList());
        }
        return orderDto;
    }
}
