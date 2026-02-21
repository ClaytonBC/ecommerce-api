package com.claytonbc.ecommerce.service;

import com.claytonbc.ecommerce.dto.CustomerRequestDTO;
import com.claytonbc.ecommerce.dto.CustomerResponseDTO;
import com.claytonbc.ecommerce.entity.Customer;
import com.claytonbc.ecommerce.exception.BusinessException;
import com.claytonbc.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponseDTO create(CustomerRequestDTO dto) {

        if (customerRepository.existsByEmail(dto.email())) {
            throw new BusinessException("Email already in use");
        }

        Customer customer = new Customer();
        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setPhone(dto.phone());

        Customer saved = customerRepository.save(customer);

        return new CustomerResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getPhone()
        );
    }
    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }
    private CustomerResponseDTO toResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
