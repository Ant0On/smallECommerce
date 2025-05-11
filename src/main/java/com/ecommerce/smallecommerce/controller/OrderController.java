package com.ecommerce.smallecommerce.controller;

import com.ecommerce.smallecommerce.model.Order;
import com.ecommerce.smallecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/get/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }
}
