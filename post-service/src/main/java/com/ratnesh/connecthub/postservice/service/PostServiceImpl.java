package com.ratnesh.connecthub.postservice.service;

import com.ratnesh.connecthub.commonlib.error.ResourceNotFoundException;
import com.ratnesh.connecthub.commonlib.security.AuthUtil;
import com.ratnesh.connecthub.postservice.client.ConnectionsServiceClient;
import com.ratnesh.connecthub.postservice.dto.PostCreateRequestDto;
import com.ratnesh.connecthub.postservice.dto.PostDto;
import com.ratnesh.connecthub.postservice.entity.Post;
import com.ratnesh.connecthub.commonlib.event.PostCreatedEvent;
import com.ratnesh.connecthub.postservice.mapper.PostMapper;
import com.ratnesh.connecthub.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final AuthUtil authUtil;
    private final KafkaTemplate<Long,Object> kafkaTemplate;
    private final ConnectionsServiceClient connectionsServiceClient;


    public PostDto createPost(PostCreateRequestDto postCreateRequestDto) {
        Long userId = authUtil.getCurrentUserId();
        Post post = Post.builder()
                .userId(userId)
                .content(postCreateRequestDto.content())
                .build();
        post = postRepository.save(post);



            PostCreatedEvent postCreatedEvent = PostCreatedEvent.builder()
                    .postId(post.getId())
                    .ownerUserId(userId)
                    .createdAt(Instant.now())
                    .build();

            kafkaTemplate.send("post-created", postCreatedEvent);

        return postMapper.toPostDto(post);

    }

    public PostDto updatePost(Long postId, PostCreateRequestDto request) {
        Long currentUserId = authUtil.getCurrentUserId();

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "post",
                                postId.toString()
                        )
                );

        if (!post.getUserId().equals(currentUserId)) {
            throw new AccessDeniedException(
                    "You are not authorized to update this post."
            );
        }

        post.setContent(request.content());

        Post updatedPost = postRepository.save(post);

        return postMapper.toPostDto(updatedPost);
    }

    public List<PostDto> getFeed() {
        List<Post> posts =
                postRepository.findAllByOrderByCreatedAtDesc();

        return postMapper.toListOfPostDto(posts);
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
            throw new AccessDeniedException("You are not authorized to delete this post.");
        }

        postRepository.delete(post);
    }
}
