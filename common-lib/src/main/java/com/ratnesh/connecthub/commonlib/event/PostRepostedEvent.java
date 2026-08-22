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
public class PostRepostedEvent {

    private Long postId;
    private Long postOwnerId;
    private Long repostedByUserId;
    private Instant createdAt;
}