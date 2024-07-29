package com.ecommerce.application.controller;

import com.ecommerce.application.auth.LoginResponse;
import com.ecommerce.application.config.JwtFilter;
import com.ecommerce.application.dto.UserDto;
import com.ecommerce.application.dto.UserLoginDto;
import com.ecommerce.application.dto.UserProfileDto;
import com.ecommerce.application.dto.UserRegistrationDto;
import com.ecommerce.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserDto> getProfile(@PathVariable UUID userId) {
        var userProfileDto = userService.getUserProfile(userId);
        return ResponseEntity.ok(userProfileDto);
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<UserDto> updateProfile(@PathVariable UUID userId, @RequestBody UserProfileDto userProfileDto) {
        var updateUserProfile = userService.updateUserProfile(userId, userProfileDto);
        return ResponseEntity.ok(updateUserProfile);
    }
}
