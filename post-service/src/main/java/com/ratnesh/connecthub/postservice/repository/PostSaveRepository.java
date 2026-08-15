package com.ratnesh.connecthub.postservice.repository;

import com.ratnesh.connecthub.postservice.entity.PostSave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostSaveRepository extends JpaRepository<PostSave, Long> {

    boolean existsByUserIdAndPostId(Long userId, Long postId);

    void deleteByUserIdAndPostId(Long userId, Long postId);
}