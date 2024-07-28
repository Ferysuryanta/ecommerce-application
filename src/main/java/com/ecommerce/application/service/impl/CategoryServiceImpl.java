package com.ecommerce.application.service.impl;

import com.ecommerce.application.dto.CategoryDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.handler.BusinessErrorCodes;
import com.ecommerce.application.mapper.CategoryMapper;
import com.ecommerce.application.repository.CategoryRepository;
import com.ecommerce.application.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ecommerce.application.handler.BusinessErrorCodes.CATEGORY_NOT_FOUND;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) {
        var category = categoryMapper.toCategory(categoryDto);
        var savedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryDto(savedCategory);
    }

    @Override
    public Optional<CategoryDto> getCategoryById(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::toCategoryDto);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryDto)
                .toList();
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(UUID categoryId, CategoryDto categoryDto) {
        if (categoryRepository.existsById(categoryId)) {
            var category = categoryMapper.toCategory(categoryDto);
            category.setCategoryId(categoryId);
            var savedCategory = categoryRepository.save(category);
            return categoryMapper.toCategoryDto(savedCategory);
        } else {
            throw new ResourceNotFoundException(CATEGORY_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteCategory(UUID categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new ResourceNotFoundException(CATEGORY_NOT_FOUND);
        }
    }
}
