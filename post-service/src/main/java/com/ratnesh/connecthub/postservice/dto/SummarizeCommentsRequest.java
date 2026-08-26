package com.ratnesh.connecthub.postservice.dto;

import java.util.List;

public record SummarizeCommentsRequest(
        List<String> comments
) {}