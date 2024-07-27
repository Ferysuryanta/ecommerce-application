package com.ecommerce.application.service;

import com.ecommerce.application.dto.CartDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartService {

    CartDto createCart(CartDto cartDto);
    Optional<CartDto> getCartById(UUID cartId);
    List<CartDto> getAllCarts();
    CartDto updateCart(UUID cartId, CartDto cartDto);
    void deleteCart(UUID cartId);
    CartDto getCartByUserId(UUID userId);
}
