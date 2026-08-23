package com.ratnesh.connecthub.postservice.controller;

import com.ratnesh.connecthub.postservice.dto.CommentDto;
import com.ratnesh.connecthub.postservice.dto.CreateCommentRequest;
import com.ratnesh.connecthub.postservice.service.CommentService;
import com.ratnesh.connecthub.postservice.service.PostInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interaction")
public class PostInteractionController {

    private final PostInteractionService postInteractionService;
    private final CommentService commentService;

    @PostMapping("/like/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        postInteractionService.likePost(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/like/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId) {
        postInteractionService.unlikePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/repost/{postId}")
    public ResponseEntity<Void> repostPost(@PathVariable Long postId) {
        postInteractionService.repostPost(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/repost/{postId}")
    public ResponseEntity<Void> unrepostPost(@PathVariable Long postId) {
        postInteractionService.unrepostPost(postId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save/{postId}")
    public ResponseEntity<Void> savePost(@PathVariable Long postId) {
        postInteractionService.savePost(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/save/{postId}")
    public ResponseEntity<Void> unsavePost(@PathVariable Long postId) {
        postInteractionService.unsavePost(postId);
        return ResponseEntity.noContent().build();
    }



    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable Long postId,
            @RequestBody CreateCommentRequest request
    ) {
        return ResponseEntity.ok(
                commentService.createComment(postId, request.content())
        );
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getComments(
            @PathVariable Long postId
    ) {
        return ResponseEntity.ok(
                commentService.getComments(postId)
        );
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.noContent().build();
    }


}

