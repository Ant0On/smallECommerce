package com.ecommerce.smallecommerce.service;

import com.ecommerce.smallecommerce.model.Order;
import com.ecommerce.smallecommerce.model.Product;
import com.ecommerce.smallecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order addOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());

        double totalPrice = order.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
