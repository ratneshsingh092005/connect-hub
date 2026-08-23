package com.ratnesh.connecthub.postservice.mapper;

import com.ratnesh.connecthub.postservice.dto.PostCreateRequestDto;
import com.ratnesh.connecthub.postservice.dto.PostDto;
import com.ratnesh.connecthub.postservice.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "imageUrls", source = "imageUrls")
    PostDto toPostDto(Post post);
    @Mapping(target = "imageUrls", source = "imageUrls")
    List<PostDto> toListOfPostDto(List<Post> posts);

}
