package com.example.modoo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String title;
    private String condition;
    private String description;
    private Double price;
    private Boolean acceptOffers;
}
