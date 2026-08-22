package com.ratnesh.connecthub.postservice.service;


import com.ratnesh.connecthub.postservice.dto.PostCreateRequestDto;
import com.ratnesh.connecthub.postservice.dto.PostDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    PostDto createPost(PostCreateRequestDto request, List<MultipartFile> files);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPostsOfUser(Long userId);

    void deletePost(Long postId);

    PostDto updatePost(Long postId, PostCreateRequestDto request);

    List<PostDto> getFeed();
}
