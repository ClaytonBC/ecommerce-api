package com.claytonbc.ecommerce.dto;

import java.util.List;

public record OrderRequestDTO(
        Long customerId,
        List<OrderItemRequestDTO> items
) {
}
