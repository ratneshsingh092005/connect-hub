package com.ratnesh.connecthub.postservice.mapper;

import com.ratnesh.connecthub.postservice.dto.PostCreateRequestDto;
import com.ratnesh.connecthub.postservice.dto.PostDto;
import com.ratnesh.connecthub.postservice.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto toPostDto(Post post);

    List<PostDto> toListOfPostDto(List<Post> posts);

}
