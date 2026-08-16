package com.ratnesh.connecthub.user_service.dto;

public record AuthResponse(
        String accessToken,
        UserProfileResponse userProfileResponse
) {
}
