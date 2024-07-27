package com.ecommerce.application.mapper;

import com.ecommerce.application.dto.CartItemDto;
import com.ecommerce.application.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {
    public CartItemDto toCartItemDto(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCartItemId(cartItem.getCartItemId());
        cartItemDto.setCart(new CartMapper().toCartDto(cartItem.getCart()));
        cartItemDto.setProduct(new ProductMapper().toProductDto(cartItem.getProduct()));
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }

    public CartItem toCartItem(CartItemDto cartItemDto) {
        if (cartItemDto == null) {
            return null;
        }
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(cartItemDto.getCartItemId());
        cartItem.setCart(new CartMapper().toCart(cartItemDto.getCart()));
        cartItem.setProduct(new ProductMapper().toProduct(cartItemDto.getProduct()));
        return cartItem;
    }
}
