package com.claytonbc.ecommerce.controller;

import com.claytonbc.ecommerce.dto.OrderRequestDTO;
import com.claytonbc.ecommerce.dto.OrderResponseDTO;
import com.claytonbc.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDTO create(@RequestBody @Valid OrderRequestDTO dto) {
        return orderService.create(dto);
    }

    @GetMapping
    public Page<OrderResponseDTO> findAll(
            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            )
            Pageable pageable
    ) {
        return orderService.findAll(pageable);
    }
}
