package com.ratnesh.connecthub.postservice.mapper;

import com.ratnesh.connecthub.postservice.dto.CommentDto;
import com.ratnesh.connecthub.postservice.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CommentMapper {
    CommentDto toDto(Comment comment);
}
