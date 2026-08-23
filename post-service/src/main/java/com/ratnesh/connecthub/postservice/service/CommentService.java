package com.ratnesh.connecthub.postservice.service;

import com.ratnesh.connecthub.postservice.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void deleteComment(Long postId, Long commentId);

    List<CommentDto> getComments(Long postId);

    CommentDto createComment(Long postId, String content);
}
