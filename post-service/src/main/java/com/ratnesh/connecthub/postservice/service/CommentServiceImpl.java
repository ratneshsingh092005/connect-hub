package com.ratnesh.connecthub.postservice.service;

import com.ratnesh.connecthub.commonlib.error.BadRequestException;
import com.ratnesh.connecthub.commonlib.error.ResourceNotFoundException;
import com.ratnesh.connecthub.commonlib.event.CommentCreatedEvent;
import com.ratnesh.connecthub.commonlib.security.AuthUtil;
import com.ratnesh.connecthub.postservice.dto.CommentDto;
import com.ratnesh.connecthub.postservice.entity.Comment;
import com.ratnesh.connecthub.postservice.entity.Post;
import com.ratnesh.connecthub.postservice.mapper.CommentMapper;
import com.ratnesh.connecthub.postservice.repository.CommentRepository;
import com.ratnesh.connecthub.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AuthUtil authUtil;
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final KafkaTemplate<Long,CommentCreatedEvent> kafkaTemplate;

    @Override
    public CommentDto createComment(Long postId, String content) {

        Long userId = authUtil.getCurrentUserId();

        Post post = postRepository.findById(postId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post", postId.toString()));

        Comment comment = Comment.builder()
                .postId(postId)
                .userId(userId)
                .content(content)
                .build();

        comment = commentRepository.save(comment);

        if (!userId.equals(post.getUserId())) {
            CommentCreatedEvent event = CommentCreatedEvent.builder()
                    .commentId(comment.getId())
                    .postId(postId)
                    .commenterId(userId)
                    .postOwnerId(post.getUserId())
                    .createdAt(comment.getCreatedAt())
                    .build();

            kafkaTemplate.send("comment-created", event);
        }

        return commentMapper.toDto(comment);
    }

    @Override
    public List<CommentDto> getComments(Long postId) {

        return commentRepository
                .findByPostIdOrderByCreatedAtDesc(postId)
                .stream()
                .map(commentMapper::toDto)
                .toList();
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {

        Long userId = authUtil.getCurrentUserId();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment",commentId.toString()));

        if (!comment.getPostId().equals(postId)) {
            throw new ResourceNotFoundException("Comment",commentId.toString());
        }

        if (!comment.getUserId().equals(userId)) {
            throw new BadRequestException("You can only delete your own comment");
        }

        commentRepository.delete(comment);
    }
}