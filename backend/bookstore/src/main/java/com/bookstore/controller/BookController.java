package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    private boolean isAdmin(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("adminUser");
        return claims != null && "admin".equals(claims.get("role"));
    }

    @PostMapping("/create-book")
    public ResponseEntity<?> createBook(@RequestBody Book book, HttpServletRequest request) {
        if (!isAdmin(request)) return ResponseEntity.status(403).body("Unauthorized");
        System.out.println("JWT adminUser claims: " + request.getAttribute("adminUser"));

        return ResponseEntity.ok(bookService.create(book));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable String id) {
        Book book = bookService.getById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.status(404).body("Book not found");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody Book book, HttpServletRequest request) {
        if (!isAdmin(request)) return ResponseEntity.status(403).body("Unauthorized");
        Book updated = bookService.update(id, book);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.status(404).body("Book not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id, HttpServletRequest request) {
        if (!isAdmin(request)) return ResponseEntity.status(403).body("Unauthorized");
        return bookService.delete(id) ? ResponseEntity.ok("Deleted") : ResponseEntity.status(404).body("Book not found");
    }
}
