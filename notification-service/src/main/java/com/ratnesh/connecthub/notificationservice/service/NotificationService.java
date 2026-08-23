package com.ratnesh.connecthub.notificationservice.service;
import com.ratnesh.connecthub.notificationservice.dto.NotificationResponse;

import com.ratnesh.connecthub.commonlib.event.*;

import java.util.List;

public interface NotificationService {
    void handlePostCreated(PostCreatedEvent event);

    void handlePostLiked(PostLikedEvent event);

    void handlePostReposted(PostRepostedEvent event);

    void handleConnectionRequestSent(ConnectionRequestSentEvent event);

    void handleConnectionRequestAccepted(ConnectionRequestAcceptedEvent event);

    List<NotificationResponse> getNotifications();

    NotificationResponse markAsRead(Long id);

    void markAllAsRead();
}
