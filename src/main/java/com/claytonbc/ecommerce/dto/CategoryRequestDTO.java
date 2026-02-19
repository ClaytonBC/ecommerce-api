package com.claytonbc.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description
) { }
