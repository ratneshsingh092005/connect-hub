package com.ratnesh.connecthub.user_service.controller;

import com.ratnesh.connecthub.user_service.Service.AuthService;
import com.ratnesh.connecthub.user_service.Service.UserProfileService;
import com.ratnesh.connecthub.user_service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final UserProfileService userProfileService;

    @PostMapping("/auth/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignupRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserSearchResponse>> searchUsers(
            @RequestParam String query
    ) {
        return ResponseEntity.ok(
                userProfileService.searchUsers(query)
        );
    }

    @PostMapping("/profile-image")
    public ResponseEntity<String> uploadProfileImage(
            @RequestParam("file") MultipartFile file
    ) {
        return ResponseEntity.ok(
                userProfileService.uploadProfileImage(file)
        );
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getMyProfile() {
        return ResponseEntity.ok(userProfileService.getMyProfile());
    }

    @PatchMapping("/profile")
    public ResponseEntity<UserProfileResponse> updateProfile(
            @RequestBody UpdateProfileRequest request
    ) {
        return ResponseEntity.ok(userProfileService.updateProfile(request));
    }
}

