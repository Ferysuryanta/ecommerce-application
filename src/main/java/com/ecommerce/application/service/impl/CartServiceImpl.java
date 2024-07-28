package com.ecommerce.application.service.impl;

import com.ecommerce.application.dto.CartDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.mapper.CartMapper;
import com.ecommerce.application.repository.CartRepository;
import com.ecommerce.application.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ecommerce.application.handler.BusinessErrorCodes.CART_NOT_FOUND;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    @Transactional
    public CartDto createCart(CartDto cartDto) {
        var cart = cartMapper.toCart(cartDto);
        var saveCart = cartRepository.save(cart);
        return cartMapper.toCartDto(saveCart);
    }

    @Override
    public Optional<CartDto> getCartById(UUID cartId) {
        return cartRepository.findById(cartId)
                .map(cartMapper::toCartDto);
    }

    @Override
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::toCartDto)
                .toList();
    }

    @Override
    @Transactional
    public CartDto updateCart(UUID cartId, CartDto cartDto) {
        if (cartRepository.existsById(cartId)) {
            var cart = cartMapper.toCart(cartDto);
            cart.setCartId(cartId);
            var updatedCart = cartRepository.save(cart);
            return cartMapper.toCartDto(updatedCart);
        } else {
            throw new ResourceNotFoundException(CART_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteCart(UUID cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
        } else {
            throw new ResourceNotFoundException(CART_NOT_FOUND);
        }
    }

    @Override
    public CartDto getCartByUserId(UUID userId) {
        return cartRepository.findByUser_UserId(userId)
                .map(cartMapper::toCartDto)
                .orElseThrow(() -> new ResourceNotFoundException(CART_NOT_FOUND));
    }
}
