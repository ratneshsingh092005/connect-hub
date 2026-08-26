package com.ratnesh.connecthub.postservice.dto;

import java.util.List;

public record CommentSummaryResponse(
        String summary,
        List<String> key_points
) {}