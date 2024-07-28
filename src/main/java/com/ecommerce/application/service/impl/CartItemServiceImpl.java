package com.ecommerce.application.service.impl;

import com.ecommerce.application.dto.CartItemDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.handler.BusinessErrorCodes;
import com.ecommerce.application.mapper.CartItemMapper;
import com.ecommerce.application.repository.CartItemRepository;
import com.ecommerce.application.service.CartItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ecommerce.application.handler.BusinessErrorCodes.CART_ITEM_NOT_FOUND;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, CartItemMapper cartItemMapper) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
    }

    @Override
    @Transactional
    public CartItemDto createCartItem(CartItemDto cartItemDto) {
        var cartItem = cartItemMapper.toCartItem(cartItemDto);
        var savedCartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toCartItemDto(savedCartItem);
    }

    @Override
    public Optional<CartItemDto> getCartItemById(UUID cartItemId) {
        return cartItemRepository.findById(cartItemId)
                .map(cartItemMapper::toCartItemDto);
    }

    @Override
    public List<CartItemDto> getAllCartItems() {
        return cartItemRepository.findAll()
                .stream()
                .map(cartItemMapper::toCartItemDto)
                .toList();
    }

    @Override
    @Transactional
    public CartItemDto updateCartItem(UUID cartItemId, CartItemDto cartItemDto) {
        if (cartItemRepository.existsById(cartItemId)) {
            var cartItem = cartItemMapper.toCartItem(cartItemDto);
            cartItem.setCartItemId(cartItemId);
            var savedCartItem = cartItemRepository.save(cartItem);
            return cartItemMapper.toCartItemDto(savedCartItem);
        } else {
            throw new ResourceNotFoundException(CART_ITEM_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteCartItem(UUID cartItemId) {
        if (cartItemRepository.existsById(cartItemId)) {
            cartItemRepository.deleteById(cartItemId);
        } else {
            throw new ResourceNotFoundException(CART_ITEM_NOT_FOUND);
        }
    }
}
