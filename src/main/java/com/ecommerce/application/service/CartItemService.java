package com.ecommerce.application.service;

import com.ecommerce.application.dto.CartItemDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CartItemService {

    CartItemDto createCartItem(CartItemDto cartItemDto);
    Optional<CartItemDto> getCartItemById(UUID cartItemId);
    List<CartItemDto> getAllCartItems();
    CartItemDto updateCartItem(UUID cartItemId, CartItemDto cartItemDto);
    void deleteCartItem(UUID cartItemId);
}
