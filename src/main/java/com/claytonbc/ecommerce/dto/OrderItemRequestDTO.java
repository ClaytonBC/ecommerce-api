package com.claytonbc.ecommerce.dto;

public record OrderItemRequestDTO(
        Long productId,
        Integer quantity
) {
}
