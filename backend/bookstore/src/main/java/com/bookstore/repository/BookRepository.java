package com.bookstore.repository;

import com.bookstore.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    long countByTrendingTrue();
    List<Book> findByCategory(String category);
    List<Book> findByTitleContainingIgnoreCase(String keyword);
}
