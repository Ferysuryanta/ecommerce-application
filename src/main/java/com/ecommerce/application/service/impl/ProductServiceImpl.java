package com.ecommerce.application.service.impl;

import com.ecommerce.application.dto.ProductDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.mapper.ProductMapper;
import com.ecommerce.application.repository.ProductRepository;
import com.ecommerce.application.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ecommerce.application.handler.BusinessErrorCodes.PRODUCT_NOT_FOUND;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        var product = productMapper.toProduct(productDto);
        var saveProduct = productRepository.save(product);
        return productMapper.toProductDto(saveProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDto> getProductById(UUID productId) {
        return Optional.ofNullable(productRepository.findById(productId)
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    @Override
    @Transactional
    public ProductDto updateProduct(UUID productId, ProductDto productDto) {
        if (productRepository.existsById(productId)) {
            var product = productMapper.toProduct(productDto);
            product.setProductId(productId);
            var updateProducts = productRepository.save(product);
            return productMapper.toProductDto(updateProducts);
        } else {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(UUID productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND);
        }
    }
}
