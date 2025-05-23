package com.bookstore.controller;

import com.bookstore.model.Order;
import com.bookstore.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody Order order){
        order.setCreatedAt(LocalDateTime.now());
        Order saved = orderRepository.save(order);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable String email){
        List<Order> orders = orderRepository.findByEmailOrderByCreatedAtDesc(email);
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderRepository.findAll());
    }
}
