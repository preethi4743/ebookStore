package com.bookstore.controller;

import com.bookstore.model.Order;
import com.bookstore.service.OrderService;
import com.google.common.base.Strings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try{
            Order savedOrder = orderService.create(order);
            return ResponseEntity.ok(savedOrder);

        }catch(Exception e){
            return ResponseEntity.status(500).body("Failed to create the order:" + e.getMessage());
        }

    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getOrdersByEmail(@PathVariable String email) {
        if (Strings.isNullOrEmpty(email)) {
            return ResponseEntity.badRequest().body("Email is required");
        }
        List<Order> orders = orderService.getByEmail(email);
        return orders.isEmpty() ? ResponseEntity.status(404).body("No orders found") : ResponseEntity.ok(orders);
    }
}
