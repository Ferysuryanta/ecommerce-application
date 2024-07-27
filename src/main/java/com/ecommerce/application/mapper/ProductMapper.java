package com.ecommerce.application.mapper;

import com.ecommerce.application.dto.ProductDto;
import com.ecommerce.application.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setCategory(new CategoryMapper()
                .toCategoryDto(product.getCategory()));

        if (product.getOrderItems() != null) {
            productDto.setOrderItems(product.getOrderItems()
                    .stream()
                    .map(new OrderItemMapper()::toOrderItemDto)
                    .toList());
        }

        if (product.getCartItems() != null) {
            productDto.setCartItems(product.getCartItems()
                    .stream()
                    .map(new CartItemMapper()::toCartItemDto)
                    .toList());
        }

        if (product.getReviews() != null) {
            productDto.setReviews(product.getReviews()
                    .stream()
                    .map(new ReviewMapper()::toReviewDto)
                    .toList());
        }

        return productDto;
    }

    public Product toProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCategory(new CategoryMapper().toCategory(productDto.getCategory()));

        if (productDto.getOrderItems() != null) {
            product.setOrderItems(productDto.getOrderItems()
                    .stream()
                    .map(new OrderItemMapper()::toOrderItem)
                    .toList());
        }

        if (productDto.getCartItems() != null) {
            product.setCartItems(productDto.getCartItems()
                    .stream()
                    .map(new CartItemMapper()::toCartItem)
                    .toList());
        }

        if(productDto.getReviews() != null) {
            product.setReviews(productDto.getReviews()
                    .stream()
                    .map(new ReviewMapper()::toReview)
                    .toList());
        }
        return product;
    }
}
