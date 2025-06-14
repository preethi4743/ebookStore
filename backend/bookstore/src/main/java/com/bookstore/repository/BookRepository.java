package com.bookstore.repository;


import com.bookstore.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByAuthor(String author);
    List<Book> findByCategory(String category);
    long countByTrendingTrue();
}
