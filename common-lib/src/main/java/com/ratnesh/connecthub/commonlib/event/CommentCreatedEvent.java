package com.ratnesh.connecthub.commonlib.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
 public class CommentCreatedEvent {
    private Long commentId;
    private Long postId;
    private Long commenterId;
    private Long postOwnerId;
    private Instant createdAt;
}