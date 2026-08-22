package com.ratnesh.connecthub.commonlib.event;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PostCreatedEvent {

    private Long postId;
    private Long ownerUserId;
    private Instant createdAt;
}
