package com.ratnesh.connecthub.postservice.service;

import com.ratnesh.connecthub.commonlib.error.ResourceNotFoundException;
import com.ratnesh.connecthub.commonlib.security.AuthUtil;
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
    private final AuthUtil authUtil;
    public PostDto createPost(PostCreateRequestDto postCreateRequestDto) {
        Long userId = authUtil.getCurrentUserId();
        Post post = Post.builder()
                .userId(userId)
                .content(postCreateRequestDto.content())
                .build();
        post = postRepository.save(post);
        return postMapper.toPostDto(post);
    }


    public PostDto updatePost(Long postId, PostCreateRequestDto request) {
    }

    public List<PostDto> getFeed() {
    }

    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post",postId.toString()));
        Long userId = authUtil.getCurrentUserId();
        return postMapper.toPostDto(post);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        List<Post> posts = postRepository.findAllByUserId(userId);
        return postMapper.toListOfPostDto(posts);
    }

    public void deletePost(Long postId) {
        Long currentUserId = authUtil.getCurrentUserId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("post", postId.toString()));

        if (!post.getUserId().equals(currentUserId)) {
            throw new RuntimeException("You are not authorized to delete this post.");
        }

        postRepository.delete(post);
    }
}
