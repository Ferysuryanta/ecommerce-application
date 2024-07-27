package com.ecommerce.application.mapper;

import com.ecommerce.application.dto.CategoryDto;
import com.ecommerce.application.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto toCategoryDto(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        if (category.getProducts() != null) {
            categoryDto.setProducts(category.getProducts()
                    .stream()
                    .map(new ProductMapper()::toProductDto)
                    .toList());
        }
        return categoryDto;
    }

    public Category toCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(category.getCategoryName());
        if (categoryDto.getProducts() != null) {
            category.setProducts(categoryDto.getProducts()
                    .stream()
                    .map(new ProductMapper()::toProduct)
                    .toList());
        }
        return category;
    }
}
