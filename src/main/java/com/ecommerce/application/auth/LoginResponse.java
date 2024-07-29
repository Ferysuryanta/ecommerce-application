package com.ecommerce.application.auth;

import com.ecommerce.application.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private UserDto user;
    private String jwtToken;
    private String refreshToken;

}
