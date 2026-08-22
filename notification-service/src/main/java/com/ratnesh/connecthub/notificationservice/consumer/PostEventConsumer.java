package com.ratnesh.connecthub.notificationservice.consumer;

import com.ratnesh.connecthub.commonlib.event.PostCreatedEvent;
import com.ratnesh.connecthub.commonlib.event.PostLikedEvent;
import com.ratnesh.connecthub.commonlib.event.PostRepostedEvent;
import com.ratnesh.connecthub.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PostEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "post-created", groupId = "notification-service")
    public void consumePostCreated(PostCreatedEvent event) {
        notificationService.handlePostCreated(event);
    }

    @KafkaListener(topics = "post-liked", groupId = "notification-service")
    public void consumePostLiked(PostLikedEvent event) {
        notificationService.handlePostLiked(event);
    }

    @KafkaListener(topics = "post-reposted", groupId = "notification-service")
    public void consumePostReposted(PostRepostedEvent event) {
        notificationService.handlePostReposted(event);
    }
}