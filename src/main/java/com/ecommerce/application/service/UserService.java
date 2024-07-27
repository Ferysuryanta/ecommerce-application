package com.ecommerce.application.service;

import com.ecommerce.application.dto.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    UserDto createUser(UserDto userDto);
    Optional<UserDto> getUserById(UUID userId);
    List<UserDto> getAllUser();
    UserDto updateUser(UUID userId, UserDto userDto);
    void deleteUser(UUID userId);
}
