package com.ratnesh.connecthub.postservice.dto;

import java.time.Instant;

public record PostDto(
        Long id,
        String content,
        Long userId,
        Instant createdAt
) {

}
