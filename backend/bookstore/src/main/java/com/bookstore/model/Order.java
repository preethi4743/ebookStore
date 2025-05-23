package com.bookstore.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private Address address;

    private List<String> productIds;

    @NotNull
    private Long phone;

    @NotNull
    private Double totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;
}
