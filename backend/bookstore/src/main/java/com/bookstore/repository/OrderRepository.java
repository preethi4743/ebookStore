package com.bookstore.repository;

import com.bookstore.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByEmailOrderByCreatedAtDesc(String email);
    List<Order> findAllByOrderByCreatedAtDesc();
    long countByEmail(String email);
}
