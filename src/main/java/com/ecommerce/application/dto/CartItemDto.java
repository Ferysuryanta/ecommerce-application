package com.ecommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemDto {

    private UUID cartItemId;
    private CartDto cart;
    private ProductDto product;
    private Integer quantity;
}
