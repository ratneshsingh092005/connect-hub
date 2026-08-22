package com.ratnesh.connecthub.postservice.dto;

import java.time.Instant;
import java.util.List;

public record PostDto(
        Long id,
        String content,
        List<String> imageUrl,
        Long userId,
        Instant createdAt
) {

}
