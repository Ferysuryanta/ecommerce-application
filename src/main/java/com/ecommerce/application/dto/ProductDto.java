package com.ecommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    private UUID productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private CategoryDto category;
    private List<OrderItemDto> orderItems;
    private List<CartItemDto> cartItems;
    private List<ReviewDto> reviews;
}
