package com.claytonbc.ecommerce.service;

import com.claytonbc.ecommerce.dto.ProductRequestDTO;
import com.claytonbc.ecommerce.dto.ProductResponseDTO;
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

    public ProductResponseDTO create(ProductRequestDTO dto){

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new BusinessException("Category not found"));

        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);
        return toResponseDTO(savedProduct);
    }

    public List<ProductResponseDTO> findAll(){
        return productRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }
    public ProductResponseDTO findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Product not found"));

        return toResponseDTO(product);
    }
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Product not found"));

        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new BusinessException("Category not found"));

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setCategory(category);

        Product updated = productRepository.save(product);

        return toResponseDTO(updated);
    }
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Product not found"));

        productRepository.delete(product);
    }

}
