package com.ecommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {

    private UUID orderItemId;
    private OrderDto order;
    private ProductDto product;
    private Integer quantity;
    private BigDecimal price;
}
