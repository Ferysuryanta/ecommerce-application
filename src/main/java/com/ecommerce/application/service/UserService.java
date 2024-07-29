package com.ecommerce.application.service;

import com.ecommerce.application.auth.LoginResponse;
import com.ecommerce.application.dto.UserDto;
import com.ecommerce.application.dto.UserLoginDto;
import com.ecommerce.application.dto.UserProfileDto;
import com.ecommerce.application.dto.UserRegistrationDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<LoginResponse> loginUser(UserLoginDto userLoginDto);
    UserDto createUser(UserDto userDto);
    Optional<UserDto> getUserById(UUID userId);
    List<UserDto> getAllUser();
    UserDto updateUser(UUID userId, UserDto userDto);
    void deleteUser(UUID userId);
    UserDto registerUser(UserRegistrationDto userRegistrationDto);
    UserDto getUserProfile(UUID userId);
    UserDto updateUserProfile(UUID userId, UserProfileDto userProfileDto);
}
