package com.claytonbc.ecommerce.service;

import com.claytonbc.ecommerce.dto.OrderItemRequestDTO;
import com.claytonbc.ecommerce.dto.OrderRequestDTO;
import com.claytonbc.ecommerce.dto.OrderResponseDTO;
import com.claytonbc.ecommerce.entity.Customer;
import com.claytonbc.ecommerce.entity.Order;
import com.claytonbc.ecommerce.entity.Product;
import com.claytonbc.ecommerce.exception.BusinessException;
import com.claytonbc.ecommerce.repository.CustomerRepository;
import com.claytonbc.ecommerce.repository.OrderRepository;
import com.claytonbc.ecommerce.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    private Customer customer;
    private Product product;

    @BeforeEach
    void setup() {
        customer = new Customer();
        customer.setId(1L);

        product = new Product();
        product.setId(1L);
        product.setPrice(new BigDecimal("100"));
    }

    @Test
    void shouldCreateOrderAndCalculateTotalCorrectly() {

        OrderItemRequestDTO itemDTO =
                new OrderItemRequestDTO(1L, 2);
        OrderRequestDTO request =
                new OrderRequestDTO(1L, List.of(itemDTO));

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product));

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        OrderResponseDTO response = orderService.create(request);
        assertNotNull(response);
        assertEquals(new BigDecimal("200"), response.totalAmount());

        verify(orderRepository, times(1))
                .save(any(Order.class));

    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

        OrderItemRequestDTO itemDTO =
                new OrderItemRequestDTO(1L, 2);

        OrderRequestDTO request =
                new OrderRequestDTO(1L, List.of(itemDTO));

        when(customerRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> {
            orderService.create(request);
        });

        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {

        OrderItemRequestDTO itemDTO =
                new OrderItemRequestDTO(1L, 2);

        OrderRequestDTO request =
                new OrderRequestDTO(1L, List.of(itemDTO));

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        when(productRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> {
            orderService.create(request);
        });

        verify(orderRepository, never())
                .save(any(Order.class));

    }
}
