package com.ratnesh.connecthub.user_service.Service;


import com.ratnesh.connecthub.user_service.dto.AuthResponse;
import com.ratnesh.connecthub.user_service.dto.LoginRequest;
import com.ratnesh.connecthub.user_service.dto.SignupRequest;
import com.ratnesh.connecthub.user_service.dto.UserProfileResponse;

public interface AuthService {
    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
