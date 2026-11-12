package com.example.DocMind.service;

import com.example.DocMind.dto.RegisterUserRequest;
import com.example.DocMind.dto.UserDto;
import com.example.DocMind.entity.Role;
import com.example.DocMind.entity.User;
import com.example.DocMind.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto registerUser(@Valid RegisterUserRequest registerUserRequest) {

        //validation
        if(registerUserRequest.username().isBlank()){
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if(userRepository.existsByUsername(registerUserRequest.username())){
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User();
        user.setUsername(registerUserRequest.username());
        user.setEmail(registerUserRequest.email());
        user.setPassword(passwordEncoder.encode(registerUserRequest.password()));
        user.setRole(Role.USER);
        User savedUser = userRepository.save(user);
        return new UserDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getRole());
    }
}
