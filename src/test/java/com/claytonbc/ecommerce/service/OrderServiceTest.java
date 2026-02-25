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
    @Test
    void shouldCalculateTotalForMultipleItems() {

        // Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product A");
        product1.setPrice(new BigDecimal("100"));

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product B");
        product2.setPrice(new BigDecimal("50"));

        OrderItemRequestDTO item1 =
                new OrderItemRequestDTO(1L, 2); // 200

        OrderItemRequestDTO item2 =
                new OrderItemRequestDTO(2L, 3); // 150

        OrderRequestDTO request =
                new OrderRequestDTO(1L, List.of(item1, item2));

        when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));

        when(productRepository.findById(1L))
                .thenReturn(Optional.of(product1));

        when(productRepository.findById(2L))
                .thenReturn(Optional.of(product2));

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        OrderResponseDTO response = orderService.create(request);

        // Assert
        assertEquals(new BigDecimal("350"), response.totalAmount());
        assertEquals(2, response.items().size());

        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
