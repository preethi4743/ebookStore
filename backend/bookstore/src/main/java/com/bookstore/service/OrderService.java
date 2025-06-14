package com.bookstore.service;

import com.bookstore.model.Book;
import com.bookstore.model.Order;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private BookRepository bookRepository;

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(Order order) {
        if (order.getProductIds() == null || order.getProductIds().isEmpty()) {
            throw new IllegalArgumentException("productIds must not be null or empty");
        }

        List<Book> books = bookRepository.findAllById(order.getProductIds());
        if (books.isEmpty()) {
            throw new IllegalArgumentException("No valid books found for the given productIds: " + order.getProductIds());
        }

        double total = books.stream().mapToDouble(Book::getNewPrice).sum();
        order.setTotalPrice(total);

        order.setCreatedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        return orderRepository.save(order);
    }


    public List<Order> getByEmail(String email) {

        List<Order> orders = orderRepository.findByEmailOrderByCreatedAtDesc(email);
        System.out.println("Orders fetched for email:" + email);
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Order Price: " + order.getTotalPrice());
            System.out.println("Order productID: " + order.getProductIds());
            List<Book> books = bookRepository.findAllById(order.getProductIds());
            for (Book book : books) {
                System.out.println("book Title:" + book.getTitle());
                System.out.println("Price:" + book.getNewPrice());
            }
            System.out.println("-----------------");

        }
        return orders;
    }
}
