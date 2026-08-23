package com.ratnesh.connecthub.user_service.Service;

import com.ratnesh.connecthub.user_service.dto.UpdateProfileRequest;
import com.ratnesh.connecthub.user_service.dto.UserProfileResponse;
import com.ratnesh.connecthub.user_service.dto.UserSearchResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserProfileService {
    List<UserSearchResponse> searchUsers(String query);

    String uploadProfileImage(MultipartFile file);

    UserProfileResponse getMyProfile();

    UserProfileResponse updateProfile(UpdateProfileRequest request);
}
