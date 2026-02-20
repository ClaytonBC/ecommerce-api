package com.claytonbc.ecommerce.service;

import com.claytonbc.ecommerce.dto.ProductRequestDTO;
import com.claytonbc.ecommerce.entity.Category;
import com.claytonbc.ecommerce.entity.Product;
import com.claytonbc.ecommerce.exception.BusinessException;
import com.claytonbc.ecommerce.repository.CategoryRepository;
import com.claytonbc.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product create(ProductRequestDTO dto){

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new BusinessException("Category not found"));

        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setCategory(category);

        return productRepository.save(product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
