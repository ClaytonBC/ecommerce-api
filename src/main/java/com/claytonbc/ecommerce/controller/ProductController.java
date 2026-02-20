package com.claytonbc.ecommerce.controller;

import com.claytonbc.ecommerce.dto.ProductRequestDTO;
import com.claytonbc.ecommerce.entity.Product;
import com.claytonbc.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product create(@RequestBody @Valid ProductRequestDTO dto) {
        return productService.create(dto);
    }
    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }
}
