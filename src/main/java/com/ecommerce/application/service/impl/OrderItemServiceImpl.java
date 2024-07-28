package com.ecommerce.application.service.impl;

import com.ecommerce.application.dto.OrderItemDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.mapper.OrderItemMapper;
import com.ecommerce.application.repository.OrderItemRepository;
import com.ecommerce.application.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ecommerce.application.handler.BusinessErrorCodes.ORDER_ITEM_NOT_FOUND;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
    }

    @Override
    @Transactional
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        var orderItem = orderItemMapper.toOrderItem(orderItemDto);
        var saveOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toOrderItemDto(saveOrderItem);
    }

    @Override
    public Optional<OrderItemDto> getOrderItemById(UUID orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .map(orderItemMapper::toOrderItemDto);
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        return orderItemRepository.findAll()
                .stream()
                .map(orderItemMapper::toOrderItemDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderItemDto updateOrderItem(UUID orderItemId, OrderItemDto orderItemDto) {
        if (orderItemRepository.existsById(orderItemId)) {
            var orderItem = orderItemMapper.toOrderItem(orderItemDto);
            orderItem.setOrderItemId(orderItemId);
            var updateOrderItem = orderItemRepository.save(orderItem);
            return orderItemMapper.toOrderItemDto(updateOrderItem);
        } else {
            throw new ResourceNotFoundException(ORDER_ITEM_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteOrderItem(UUID orderItemId) {
        if (orderItemRepository.existsById(orderItemId)) {
            orderItemRepository.deleteById(orderItemId);
        } else {
            throw new ResourceNotFoundException(ORDER_ITEM_NOT_FOUND);
        }
    }
}
