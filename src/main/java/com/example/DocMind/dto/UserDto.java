package com.example.DocMind.dto;

import com.example.DocMind.entity.Role;

public record UserDto(
        Long id,
        String username,
        String email,
        Role role
) {
}
