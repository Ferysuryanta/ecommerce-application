package com.ecommerce.application.mapper;

import com.ecommerce.application.dto.CartDto;
import com.ecommerce.application.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public Cart toCart(CartDto cartDto) {
      if (cartDto == null) {
          return null;
      }
      Cart cart = new Cart();
      cart.setCartId(cartDto.getCartId());
      cart.setUser(new UserMapper().toUser(cartDto.getUser()));
      if (cartDto.getCartItems() != null) {
          cart.setCartItems(cartDto.getCartItems()
                  .stream()
                  .map(new CartItemMapper()::toCartItem)
                  .toList());
      }
      return cart;
    }

    public CartDto toCartDto(Cart cart) {
        if (cart == null) {
            return null;
        }
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setUser(new UserMapper().toUserDto(cart.getUser()));

        if (cart.getCartItems() != null) {
            cartDto.setCartItems(cart.getCartItems()
                    .stream()
                    .map(new CartItemMapper()::toCartItemDto)
                    .toList());
        }
        return cartDto;
    }
}
