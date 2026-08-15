package com.ratnesh.connecthub.postservice.repository;

import com.ratnesh.connecthub.postservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
