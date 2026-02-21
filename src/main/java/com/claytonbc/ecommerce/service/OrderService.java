package com.claytonbc.ecommerce.service;

import com.claytonbc.ecommerce.dto.OrderItemRequestDTO;
import com.claytonbc.ecommerce.dto.OrderItemResponseDTO;
import com.claytonbc.ecommerce.dto.OrderRequestDTO;
import com.claytonbc.ecommerce.dto.OrderResponseDTO;
import com.claytonbc.ecommerce.entity.Customer;
import com.claytonbc.ecommerce.entity.Order;
import com.claytonbc.ecommerce.entity.OrderItem;
import com.claytonbc.ecommerce.entity.Product;
import com.claytonbc.ecommerce.exception.BusinessException;
import com.claytonbc.ecommerce.repository.CustomerRepository;
import com.claytonbc.ecommerce.repository.OrderRepository;
import com.claytonbc.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderResponseDTO create(OrderRequestDTO dto) {

        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found"));
        Order order = new Order();
        order.setCustomer(customer);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequestDTO itemDto : dto.items()) {

            Product product = productRepository.findById(itemDto.productId())
                    .orElseThrow(() -> new BusinessException("Product not found"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemDto.quantity());
            item.setPrice(product.getPrice());

            items.add(item);

            BigDecimal itemTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemDto.quantity()));

            total = total.add(itemTotal);
        }

        order.setItems(items);
        order.setTotalAmount(total);

        Order saved = orderRepository.save(order);

        return new OrderResponseDTO(
                saved.getId(),
                saved.getCustomer().getId(),
                saved.getTotalAmount(),
                saved.getCreatedAt(),
                saved.getItems().stream()
                        .map(item -> new OrderItemResponseDTO(
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getPrice()
                        ))
                        .toList()
        );
    }
}
