package com.ecommerce.smallecommerce.service;

import com.ecommerce.smallecommerce.dto.OrderRequestDto;
import com.ecommerce.smallecommerce.dto.OrderResponseDto;
import com.ecommerce.smallecommerce.dto.ProductResponseDto;
import com.ecommerce.smallecommerce.model.Order;
import com.ecommerce.smallecommerce.model.Product;
import com.ecommerce.smallecommerce.repository.OrderRepository;
import com.ecommerce.smallecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderResponseDto addOrder(OrderRequestDto orderRequest) {
        List<Product> products = orderRequest.getProductIds().stream()
                        .map(id -> productRepository.findById(id).orElseThrow(
                                () -> new RuntimeException("product not find for id: " + id)
                        )).toList();

        Order order = new Order();
        order.setProducts(products);
        order.setOrderDate(LocalDateTime.now());
        double totalPrice = order.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();
        order.setTotalPrice(totalPrice);
        return toDto(orderRepository.save(order));
    }

    public OrderResponseDto getOrderById(Long id) {
        return toDto(orderRepository.findById(id).orElseThrow(
                () -> new RuntimeException("order not find for id: " + id)
        ));
    }

    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    private OrderResponseDto toDto(Order order) {
        OrderResponseDto orderResponseDto = new  OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setTotalPrice(order.getTotalPrice());

        List<ProductResponseDto> productDtos = order.getProducts().stream()
                .map(p -> new ProductResponseDto(p.getId(), p.getName(), p.getDescription(), p.getPrice()))
                .toList();
        orderResponseDto.setProducts(productDtos);
        return orderResponseDto;
    }
}
