package com.ratnesh.connecthub.postservice.controller;

import com.ratnesh.connecthub.postservice.service.PostInteractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interaction")
public class PostInteractionController {
    private final PostInteractionService postInteractionService;

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
}

