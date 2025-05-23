package com.bookstore.service;

import com.bookstore.model.Order;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.OrderRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


// Aggregates admin dashboard stats like total orders, total sales, trending books, etc.
@Service
public class AdminStatsService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();

        // 1. Total number of orders
        long totalOrders = orderRepository.count();
        stats.put("totalOrders", totalOrders);

        // 2. Total sales (sum of totalPrice)
        Aggregation totalSalesAgg = newAggregation(
                group().sum("totalPrice").as("totalSales")
        );

        AggregationResults<Document> totalSalesResult =
                mongoTemplate.aggregate(totalSalesAgg, Order.class, Document.class);

        double totalSales = totalSalesResult.getMappedResults().isEmpty()
                ? 0
                : totalSalesResult.getMappedResults().get(0).getDouble("totalSales");

        stats.put("totalSales", totalSales);

        // 3. Trending books count
        long trendingBooks = bookRepository.countByTrendingTrue();
        stats.put("trendingBooks", trendingBooks);

        // 4. Total number of books
        long totalBooks = bookRepository.count();
        stats.put("totalBooks", totalBooks);

        // 5. Monthly Sales
        Aggregation monthlySalesAgg = newAggregation(
                project("totalPrice", "createdAt")
                        .andExpression("year(createdAt)").as("year")
                        .andExpression("month(createdAt)").as("month"),
                group(fields().and("year").and("month"))
                        .sum("totalPrice").as("totalSales")
                        .count().as("totalOrders"),
                sort(Sort.Direction.ASC, "_id")
        );

        AggregationResults<Document> monthlySalesResult =
                mongoTemplate.aggregate(monthlySalesAgg, Order.class, Document.class);

        List<Map<String, Object>> monthlySales = new ArrayList<>();
        for (Document doc : monthlySalesResult) {
            Map<String, Object> entry = new HashMap<>();
            Document id = (Document) doc.get("_id");
            entry.put("year", id.getInteger("year"));
            entry.put("month", id.getInteger("month"));
            entry.put("totalSales", doc.getDouble("totalSales"));
            entry.put("totalOrders", doc.getInteger("totalOrders"));
            monthlySales.add(entry);
        }

        stats.put("monthlySales", monthlySales);

        return stats;
    }
}
