package com.ratnesh.connecthub.postservice.client;

import com.ratnesh.connecthub.postservice.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ai-service",
        url = "${ai-service.url}"
)
public interface AIServiceClient {

    @PostMapping("/ai/improve-post")
    ImprovePostResponse improvePost(
            @RequestBody ImprovePostRequest request
    );

    @PostMapping("/ai/generate-post")
    GeneratePostResponse generatePost(
            @RequestBody GeneratePostRequest request
    );

    @PostMapping("/ai/summarize-comments")
    CommentSummaryResponse summarizeComments(
            @RequestBody SummarizeCommentsRequest request
    );
}