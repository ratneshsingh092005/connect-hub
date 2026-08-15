package com.ratnesh.connecthub.postservice.service;

import com.ratnesh.connecthub.postservice.dto.PostCreateRequestDto;
import com.ratnesh.connecthub.postservice.dto.PostDto;
import com.ratnesh.connecthub.postservice.entity.Post;
import com.ratnesh.connecthub.postservice.mapper.PostMapper;
import com.ratnesh.connecthub.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto) {
        Long userId = 1L;
        Post post = Post.builder()
                .userId(userId)
                .content(postCreateRequestDto.content())
                .build();
        post = postRepository.save(post);
        return postMapper.toPostDto(post);
    }


}
