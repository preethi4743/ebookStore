package com.bookstore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {
    private String title;
    private String description;
    private String category;
    private boolean trending;
    private String coverImage;
    private Number oldPrice;
    private Number newPrice;
}
