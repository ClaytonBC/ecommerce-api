package com.claytonbc.ecommerce.service;

import com.claytonbc.ecommerce.dto.CategoryRequestDTO;
import com.claytonbc.ecommerce.entity.Category;
import com.claytonbc.ecommerce.exception.BusinessException;
import com.claytonbc.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category create(CategoryRequestDTO dto) {

        if (categoryRepository.existsByName(dto.name())) {
            throw new BusinessException("Category already exists");
        }

        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());

        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Category not found"));
    }

    public Category update(Long id, CategoryRequestDTO dto) {

        Category category = findById(id);

        if (!category.getName().equals(dto.name()) &&
                categoryRepository.existsByName(dto.name())) {
            throw new BusinessException("Category name already in use");
        }

        category.setName(dto.name());
        category.setDescription(dto.description());

        return categoryRepository.save(category);
    }
    public void delete(Long id) {

        Category category = findById(id);

        categoryRepository.delete(category);
    }
}
