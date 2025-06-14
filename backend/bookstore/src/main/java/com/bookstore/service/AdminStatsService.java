package com.bookstore.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminStatsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();

        // 1. Total Orders
        long totalOrders = mongoTemplate.getCollection("orders").countDocuments();
        stats.put("totalOrders", totalOrders);

        // 2. Total Sales
        Aggregation totalSalesAgg = Aggregation.newAggregation(
                Aggregation.group().sum("totalPrice").as("totalSales")
        );
        AggregationResults<Document> salesResults = mongoTemplate.aggregate(totalSalesAgg, "orders", Document.class);
        double totalSales = salesResults.getUniqueMappedResult() != null
                ? salesResults.getUniqueMappedResult().getDouble("totalSales")
                : 0;
        stats.put("totalSales", totalSales);

        // 3. Trending Books Count
        Aggregation trendingAgg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("trending").is(true)),
                Aggregation.count().as("trendingBooksCount")
        );
        AggregationResults<Document> trendingResults = mongoTemplate.aggregate(trendingAgg, "book", Document.class);
        int trendingBooks = trendingResults.getUniqueMappedResult() != null
                ? trendingResults.getUniqueMappedResult().getInteger("trendingBooksCount")
                : 0;
        stats.put("trendingBooks", trendingBooks);

        // 4. Total Books
        long totalBooks = mongoTemplate.getCollection("book").countDocuments();
        stats.put("totalBooks", totalBooks);

        // 5. Monthly Sales
        Aggregation monthlyAgg = Aggregation.newAggregation(
                Aggregation.project()
                        .andExpression("year(createdAt)").as("year")
                        .andExpression("month(createdAt)").as("month")
                        .and("totalPrice").as("totalPrice"),
                Aggregation.group("year", "month")
                        .sum("totalPrice").as("totalSales")
                        .count().as("totalOrders"),
                Aggregation.sort(Sort.by(Sort.Direction.ASC, "_id"))
        );

        List<Document> monthlySales = mongoTemplate.aggregate(monthlyAgg, "orders", Document.class).getMappedResults();
        stats.put("monthlySales", monthlySales);

        return stats;
    }
}
