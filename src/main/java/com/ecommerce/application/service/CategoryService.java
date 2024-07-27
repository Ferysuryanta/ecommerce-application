package com.ecommerce.application.service;

import com.ecommerce.application.dto.CategoryDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    Optional<CategoryDto> getCategoryById(UUID categoryId);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(UUID categoryId, CategoryDto categoryDto);
    void deleteCategory(UUID categoryId);
}
