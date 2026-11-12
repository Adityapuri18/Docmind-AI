package com.example.DocMind.dto;

public record LoginRequest(
        String username,
        String password
) {
}