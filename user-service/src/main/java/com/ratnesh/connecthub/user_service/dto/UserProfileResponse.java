package com.ratnesh.connecthub.user_service.dto;

public record UserProfileResponse(
        Long id,
        String email,
        String name
) {
}
