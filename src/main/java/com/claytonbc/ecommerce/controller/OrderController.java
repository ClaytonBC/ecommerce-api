package com.claytonbc.ecommerce.controller;

import com.claytonbc.ecommerce.dto.OrderRequestDTO;
import com.claytonbc.ecommerce.dto.OrderResponseDTO;
import com.claytonbc.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDTO create(@RequestBody @Valid OrderRequestDTO dto) {
        return orderService.create(dto);
    }
}
