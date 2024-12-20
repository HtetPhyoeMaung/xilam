package com.venzee.forXilam.dto;

import lombok.Data;

@Data
public class ProductDto {
    private int id;
    private String name;
    private double price;
    private CategoryDto categoryDto;

}
