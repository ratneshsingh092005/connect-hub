package com.ratnesh.connecthub.user_service.dto;

public record SignupRequest(
        String name,
        String email,
        String password
) {
}
