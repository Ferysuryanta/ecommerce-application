package com.ecommerce.application.controller;

import com.ecommerce.application.dto.CartDto;
import com.ecommerce.application.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-cart")
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) {
        var addCart = cartService.createCart(cartDto);
        return ResponseEntity.ok(addCart);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable UUID cartId) {
        Optional<CartDto> cartDto = cartService.getCartById(cartId);
        return cartDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all-cart")
    public ResponseEntity<List<CartDto>> getAllCarts() {
        List<CartDto> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @PutMapping("/getCart/{cartId}")
    public ResponseEntity<CartDto> updateCart(@PathVariable UUID cartId, @RequestBody CartDto cartDto) {
        var updateCart = cartService.updateCart(cartId, cartDto);
        return ResponseEntity.ok(updateCart);
    }

    @DeleteMapping("/delete-cart/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable UUID cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCartByUserId(@PathVariable UUID userId) {
        var cartDto = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cartDto);
    }
}
