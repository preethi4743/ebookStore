package com.bookstore.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "books")
public class Book {
    private String id; // Assuming you have an ID field for MongoDB
    private String title;
    private String author;
    private String isbn;
    private String description;
    private String coverImage;
    private double oldPrice;
    private double newPrice;
    private String category;
    private boolean trending;
    private LocalDateTime createdAt = LocalDateTime.now();

    public boolean isTrending() {
        return trending;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Book(String id, String title, String author, String isbn, String description,
                String coverImage, double oldPrice, double newPrice,
                String category, boolean trending) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
        this.coverImage = coverImage;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.category = category;
        this.trending = trending;
    }

    public String getCoverImage() {
        return coverImage;
    }



    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getTrending() {
        return trending;
    }

    public void setTrending(boolean trending) {
        this.trending = trending;
    }

    public Book() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
