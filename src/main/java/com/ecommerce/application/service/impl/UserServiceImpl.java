package com.ecommerce.application.service.impl;

import com.ecommerce.application.auth.LoginResponse;
import com.ecommerce.application.config.JwtService;
import com.ecommerce.application.dto.UserDto;
import com.ecommerce.application.dto.UserLoginDto;
import com.ecommerce.application.dto.UserProfileDto;
import com.ecommerce.application.dto.UserRegistrationDto;
import com.ecommerce.application.exception.ResourceNotFoundException;
import com.ecommerce.application.mapper.UserMapper;
import com.ecommerce.application.model.User;
import com.ecommerce.application.repository.UserRepository;
import com.ecommerce.application.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ecommerce.application.handler.BusinessErrorCodes.USER_NOT_FOUND;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
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

    @Override
    @Transactional
    public UserDto registerUser(UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByUsername(userRegistrationDto.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userRepository.existsByEmail(userRegistrationDto.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        var user = userMapper.toUserRegistration(userRegistrationDto);
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        var savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<LoginResponse> loginUser(UserLoginDto userLoginDto) {
        Optional<User> userOpt = userRepository.findByUsername(userLoginDto.getUsername());
        if (userOpt.isPresent() && passwordEncoder.matches(userLoginDto.getPassword(), userOpt.get().getPassword())) {
            var userDto = userMapper.toUserDto(userOpt.get());
            String jwtToken = jwtService.generateToken(userOpt.get());
            String refreshToken = jwtService.generateRefreshToken(userOpt.get());
            var loginResponse = new LoginResponse(userDto, jwtToken, refreshToken);
            return Optional.of(loginResponse);
        }
        return Optional.empty();
    }

    @Override
    public UserDto getUserProfile(UUID userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUserProfile(UUID userId, UserProfileDto userProfileDto) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND));
        user.setUsername(userProfileDto.getUsername());
        user.setEmail(userProfileDto.getEmail());
        user.setFirstname(userProfileDto.getFirstname());
        user.setLastname(userProfileDto.getLastname());
        var updateUserProfile = userRepository.save(user);
        return userMapper.toUserDto(updateUserProfile);
    }
}
