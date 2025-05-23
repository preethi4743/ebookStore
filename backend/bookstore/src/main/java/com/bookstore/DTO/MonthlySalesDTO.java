package com.bookstore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySalesDTO {
    private int year;
    private int month;
    private double totalSales;
    private long totalOrders;
}
