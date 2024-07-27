package com.ecommerce.application.service;

import com.ecommerce.application.dto.ProductDto;
import com.ecommerce.application.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    Optional<ProductDto> getProductById(UUID productId);
    List<ProductDto> getAllProduct();
    ProductDto updateProduct(UUID productId, ProductDto productDto);
    void deleteProduct(UUID productId);
}
