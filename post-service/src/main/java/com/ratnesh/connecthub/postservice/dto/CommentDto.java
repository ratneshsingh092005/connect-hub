package com.ratnesh.connecthub.postservice.dto;


import java.time.Instant;

public record CommentDto(
        Long id,
        Long postId,
        Long userId,
        String content,
        Instant createdAt
) {
}