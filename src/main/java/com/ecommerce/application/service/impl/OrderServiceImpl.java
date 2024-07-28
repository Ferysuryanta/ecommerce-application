package com.ecommerce.application.service.impl;

import com.ecommerce.application.dto.OrderDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.mapper.OrderMapper;
import com.ecommerce.application.repository.OrderRepository;
import com.ecommerce.application.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ecommerce.application.handler.BusinessErrorCodes.ORDER_NOT_FOUND;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        var order = orderMapper.toOrder(orderDto);
        var saveOrder = orderRepository.save(order);
        return orderMapper.toOrderDto(saveOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderDto> getOrderById(UUID orderId) {
        return Optional.ofNullable(orderRepository.findById(orderId)
                .map(orderMapper::toOrderDto)
                .orElseThrow(() -> new ResourceNotFoundException(ORDER_NOT_FOUND)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toOrderDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto updateOrder(UUID orderId, OrderDto orderDto) {
        if (orderRepository.existsById(orderId)) {
            var order = orderMapper.toOrder(orderDto);
            order.setOrderId(orderId);
            var updateOrder = orderRepository.save(order);
            return orderMapper.toOrderDto(updateOrder);
        } else {
            throw new ResourceNotFoundException(ORDER_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteOrder(UUID orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new ResourceNotFoundException(ORDER_NOT_FOUND);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getOrderByUserId(UUID userId) {
        return orderRepository.findByUser_UserId(userId)
                .stream()
                .map(orderMapper::toOrderDto)
                .toList();
    }
}
