package com.ratnesh.connecthub.postservice.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record PostCreateRequestDto(
        String content

) {
}
