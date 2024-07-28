package com.ecommerce.application.service.impl;

import com.ecommerce.application.dto.UserDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.mapper.UserMapper;
import com.ecommerce.application.repository.UserRepository;
import com.ecommerce.application.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ecommerce.application.handler.BusinessErrorCodes.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        var user = userMapper.toUser(userDto);
        var saveUser = userRepository.save(user);
        return userMapper.toUserDto(saveUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getUserById(UUID userId) {
        return userRepository.findById(userId)
                .map(userMapper::toUserDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    @Override
    @Transactional
    public UserDto updateUser(UUID userId, UserDto userDto) {
        if (userRepository.existsById(userId)) {
            var user = userMapper.toUser(userDto);
            user.setUserId(userId);
            var updateUser = userRepository.save(user);
            return userMapper.toUserDto(updateUser);
        } else {
            throw new ResourceNotFoundException(USER_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new ResourceNotFoundException(USER_NOT_FOUND);
        }
    }
}
