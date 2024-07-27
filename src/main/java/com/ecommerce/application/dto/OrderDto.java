package com.ecommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {

    private UUID orderId;
    private UserDto user;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private String status;
    private PaymentDto payment;
    private ShippingDto shipping;
    private List<OrderItemDto> orderItems;
}
