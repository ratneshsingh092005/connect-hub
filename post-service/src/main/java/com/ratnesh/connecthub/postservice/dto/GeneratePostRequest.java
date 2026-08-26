package com.ratnesh.connecthub.postservice.dto;

public record GeneratePostRequest(
        String idea,
        String tone,
        String length
) {}