package com.example.DocMind.dto;

public record LoginResponse(
        String accessToken,
        UserDto user
) {
}