package com.ratnesh.connecthub.postservice.repository;

import com.ratnesh.connecthub.postservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostIdOrderByCreatedAtDesc(Long postId);
}
