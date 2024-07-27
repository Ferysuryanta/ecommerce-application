package com.ecommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDto {

    private UUID reviewId;
    private UserDto user;
    private ProductDto product;
    private String reviewText;
    private Integer rating;
}
