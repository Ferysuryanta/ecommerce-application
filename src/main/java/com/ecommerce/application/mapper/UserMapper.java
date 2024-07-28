package com.ecommerce.application.mapper;

import com.ecommerce.application.dto.UserDto;
import com.ecommerce.application.dto.UserProfileDto;
import com.ecommerce.application.dto.UserRegistrationDto;
import com.ecommerce.application.model.User;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setPhone(user.getPhone());

        if (user.getCart() != null) {
            userDto.setCart(new CartMapper().toCartDto(user.getCart()));
        }

        if (user.getOrders() != null) {
            userDto.setOrders(user.getOrders()
                    .stream()
                    .map(new OrderMapper()::toOrderDto)
                    .toList());
        }

        if (user.getReviews() != null) {
            userDto.setReviews(user.getReviews()
                    .stream()
                    .map(new ReviewMapper()::toReviewDto)
                    .toList());
        }

        return userDto;
    }

    public User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());

        if (userDto.getCart() != null) {
            user.setCart(new CartMapper().toCart(userDto.getCart()));
        }

        if (userDto.getOrders() != null) {
            user.setOrders(userDto.getOrders()
                    .stream()
                    .map(new OrderMapper()::toOrder)
                    .toList());
        }

        if (userDto.getReviews() != null) {
            user.setReviews(userDto.getReviews()
                    .stream()
                    .map(new ReviewMapper()::toReview)
                    .toList());
        }
        return user;
    }

    public User toUserRegistration(UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto == null) {
            return null;
        }
        User user = new User();
        user.setUsername(userRegistrationDto.getUsername());
        user.setPassword(userRegistrationDto.getPassword());
        user.setEmail(userRegistrationDto.getEmail());
        return user;
    }

    public UserProfileDto toUserProfileDto(User user) {
        if (user == null) {
            return null;
        }
        var userProfileDto = new UserProfileDto();
        userProfileDto.setUsername(user.getUsername());
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setFirstname(user.getFirstname());
        userProfileDto.setLastname(user.getLastname());
        return userProfileDto;
    }
}
