package com.ratnesh.connecthub.user_service.dto;

public record UpdateProfileRequest(
        String name,
        String bio
) {
}