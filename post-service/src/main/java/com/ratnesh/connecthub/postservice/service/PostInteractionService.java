package com.ratnesh.connecthub.postservice.service;

public interface PostInteractionService {
    void likePost(Long postId);

    void unlikePost(Long postId);

    void repostPost(Long postId);

    void savePost(Long postId);

    void unsavePost(Long postId);

    void unrepostPost(Long postId);
}
