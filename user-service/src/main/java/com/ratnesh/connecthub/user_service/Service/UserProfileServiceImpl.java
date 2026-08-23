package com.ratnesh.connecthub.user_service.Service;

import com.ratnesh.connecthub.commonlib.error.BadRequestException;
import com.ratnesh.connecthub.commonlib.error.ResourceNotFoundException;
import com.ratnesh.connecthub.commonlib.security.AuthUtil;
import com.ratnesh.connecthub.user_service.client.UploaderServiceClient;
import com.ratnesh.connecthub.user_service.dto.UpdateProfileRequest;
import com.ratnesh.connecthub.user_service.dto.UserProfileResponse;
import com.ratnesh.connecthub.user_service.dto.UserSearchResponse;
import com.ratnesh.connecthub.user_service.entity.User;
import com.ratnesh.connecthub.user_service.mapper.UserMapper;
import com.ratnesh.connecthub.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthUtil authUtil;
    private final UploaderServiceClient uploaderServiceClient;

    @Override
    public List<UserSearchResponse> searchUsers(String query) {
        return userRepository.findByNameContainingIgnoreCase(query)
                .stream()
                .map(userMapper::toSearchResponse)
                .toList();
    }


    @Override
    public String uploadProfileImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new BadRequestException("Profile image is required");
        }

        String imageUrl = uploaderServiceClient
                .uploadFile(file)
                .getBody();

        if (imageUrl == null || imageUrl.isBlank()) {
            throw new BadRequestException("Failed to upload profile image");
        }

        Long userId = authUtil.getCurrentUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User",
                                userId.toString()
                        )
                );

        user.setProfileImageUrl(imageUrl);
        userRepository.save(user);

        return imageUrl;
    }

    @Override
    public UserProfileResponse getMyProfile() {

        Long userId = authUtil.getCurrentUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));

        return new UserProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getBio(),
                user.getProfileImageUrl()
        );
    }

    @Override
    public UserProfileResponse updateProfile(UpdateProfileRequest request) {

        Long userId = authUtil.getCurrentUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User",userId.toString()));

        user.setName(request.name());
        user.setBio(request.bio());

        user = userRepository.save(user);

        return new UserProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getBio(),
                user.getProfileImageUrl()
        );
    }
}