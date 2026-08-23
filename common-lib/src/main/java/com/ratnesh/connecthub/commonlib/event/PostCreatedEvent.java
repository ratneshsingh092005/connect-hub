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
public class PostCreatedEvent {

    private Long postId;
    private Long ownerUserId;
    private Instant createdAt;
}
