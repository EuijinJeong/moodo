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

    // 사용자 식별을 위한 변수
    private String email;
}
