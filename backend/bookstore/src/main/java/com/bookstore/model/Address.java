package com.bookstore.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @NotBlank
    private String state;

    @NotBlank
    private String zipcode;
}
