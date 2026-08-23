package com.ratnesh.connecthub.user_service.mapper;


import com.ratnesh.connecthub.commonlib.security.JwtUserPrincipal;
import com.ratnesh.connecthub.user_service.dto.UserProfileResponse;
import com.ratnesh.connecthub.user_service.dto.UserSearchResponse;
import com.ratnesh.connecthub.user_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(source = "userId",target = "id")
    UserProfileResponse toUserProfileResponse(JwtUserPrincipal user);

    UserSearchResponse toSearchResponse(User user);
}
