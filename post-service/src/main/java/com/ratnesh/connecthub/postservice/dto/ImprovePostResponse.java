package com.ratnesh.connecthub.postservice.dto;

import java.util.List;

public record ImprovePostResponse(
        String content,
        List<String> hashtags
) {}