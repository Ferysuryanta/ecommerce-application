package com.ecommerce.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private UUID userId;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String address;
    private String phone;
    private CartDto cart;
    private List<OrderDto> orders;
    private List<ReviewDto> reviews;
}
