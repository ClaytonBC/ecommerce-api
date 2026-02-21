package com.claytonbc.ecommerce.dto;

public record CustomerResponseDTO(
        Long id,
        String name,
        String email,
        String phone
) { }
