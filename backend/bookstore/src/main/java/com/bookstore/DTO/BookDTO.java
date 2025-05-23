package com.bookstore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String id;
    private String title;
    private String description;
    private String category;
    private boolean trending;
    private String coverImage;
    private Number oldPrice;
    private Number newPrice;
    private LocalDateTime createdAt;
}
