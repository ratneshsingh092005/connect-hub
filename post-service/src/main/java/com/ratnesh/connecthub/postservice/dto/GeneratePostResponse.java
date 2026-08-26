package com.ratnesh.connecthub.postservice.dto;

import java.util.List;

public record GeneratePostResponse(
        String content,
        List<String> hashtags
) {}