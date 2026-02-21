package com.claytonbc.ecommerce.controller;

import com.claytonbc.ecommerce.dto.CustomerRequestDTO;
import com.claytonbc.ecommerce.dto.CustomerResponseDTO;
import com.claytonbc.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerResponseDTO create(@RequestBody @Valid CustomerRequestDTO dto){
        return customerService.create(dto);
    }
    @GetMapping
    public List<CustomerResponseDTO> findAll() {
        return customerService.findAll();
    }
}
