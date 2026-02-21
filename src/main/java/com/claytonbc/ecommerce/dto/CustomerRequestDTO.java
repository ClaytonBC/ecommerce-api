package com.claytonbc.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        String phone
) { }
