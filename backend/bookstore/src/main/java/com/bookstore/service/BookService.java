package com.bookstore.service;


import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepo;

    public Book create(Book book) {
        return bookRepo.save(book);
    }

    public List<Book> getAll() {
        return bookRepo.findAll()
                .stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .toList();
    }

    public Book getById(String id) {
        return bookRepo.findById(id).orElse(null);
    }

    public Book update(String id, Book updatedBook) {
        return bookRepo.findById(id).map(existing -> {
            updatedBook.setId(id);
            return bookRepo.save(updatedBook);
        }).orElse(null);
    }

    public boolean delete(String id) {
        return bookRepo.findById(id).map(existing -> {
            bookRepo.delete(existing);
            return true;
        }).orElse(false);
    }
}
