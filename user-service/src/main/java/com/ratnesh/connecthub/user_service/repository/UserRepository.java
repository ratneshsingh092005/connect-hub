package com.ratnesh.connecthub.user_service.repository;

import com.ratnesh.connecthub.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    List<User> findByNameContainingIgnoreCase(String query);
}
