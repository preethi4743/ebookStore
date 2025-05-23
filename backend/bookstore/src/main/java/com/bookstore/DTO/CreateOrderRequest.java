package com.bookstore.DTO;

import com.bookstore.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotNull
    private Address address;

    @NotNull
    private List<String> productIds;

    @NotNull
    private Long phone;

    @NotNull
    private Double totalPrice;
}
