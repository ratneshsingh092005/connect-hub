package com.ratnesh.connecthub.user_service.dto;

public record LoginRequest(
        String email,
        String password
) {
}
