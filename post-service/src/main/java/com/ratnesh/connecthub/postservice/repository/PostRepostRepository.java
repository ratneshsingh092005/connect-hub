package com.ratnesh.connecthub.postservice.repository;

import com.ratnesh.connecthub.postservice.entity.PostRepost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepostRepository extends JpaRepository<PostRepost, Long> {

    boolean existsByUserIdAndPostId(Long userId, Long postId);

    void deleteByUserIdAndPostId(Long userId, Long postId);
}