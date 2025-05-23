package com.bookstore.service;

import com.bookstore.model.Order;
import com.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByEmailOrderByCreatedAtDesc(email);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public long countOrdersByEmail(String email) {
        return orderRepository.countByEmail(email);
    }
}
