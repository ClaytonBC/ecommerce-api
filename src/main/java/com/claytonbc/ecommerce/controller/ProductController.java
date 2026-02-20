package com.claytonbc.ecommerce.controller;

import com.claytonbc.ecommerce.dto.ProductRequestDTO;
import com.claytonbc.ecommerce.dto.ProductResponseDTO;
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
    public ProductResponseDTO create(@RequestBody @Valid ProductRequestDTO dto) {
        return productService.create(dto);
    }
    @GetMapping
    public List<ProductResponseDTO> findAll() {
        return productService.findAll();
    }
}
