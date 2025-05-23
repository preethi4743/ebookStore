package com.bookstore.DTO;

import com.bookstore.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String id;
    private String name;
    private String email;
    private Address address;
    private List<String> productIds;
    private Long phone;
    private Double totalPrice;
    private LocalDateTime createdAt;
}
