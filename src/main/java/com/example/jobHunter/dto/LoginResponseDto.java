package com.example.jobHunter.dto;

public record LoginResponseDto(String message, UserDto user, String jwtToken) {
}
