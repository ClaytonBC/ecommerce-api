package com.claytonbc.ecommerce.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        Long categoryId,
        String categoryName
) { }
