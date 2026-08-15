package com.ratnesh.connecthub.postservice.service;


import com.ratnesh.connecthub.postservice.dto.PostCreateRequestDto;
import com.ratnesh.connecthub.postservice.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostCreateRequestDto request);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPostsOfUser(Long userId);

    void deletePost(Long postId);

    PostDto updatePost(Long postId, PostCreateRequestDto request);

    List<PostDto> getFeed();
}
