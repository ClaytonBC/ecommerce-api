package com.claytonbc.ecommerce.controller;

import com.claytonbc.ecommerce.dto.CategoryRequestDTO;
import com.claytonbc.ecommerce.entity.Category;
import com.claytonbc.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Category create(@RequestBody @Valid CategoryRequestDTO dto) {
        return categoryService.create(dto);
    }
    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id,
                           @RequestBody @Valid CategoryRequestDTO dto) {
        return categoryService.update(id, dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
