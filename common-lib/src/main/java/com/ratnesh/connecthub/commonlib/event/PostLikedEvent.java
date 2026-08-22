package com.ratnesh.connecthub.commonlib.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikedEvent {

    private Long postId;
    private Long postOwnerId;
    private Long likedByUserId;
    private Instant createdAt;
}