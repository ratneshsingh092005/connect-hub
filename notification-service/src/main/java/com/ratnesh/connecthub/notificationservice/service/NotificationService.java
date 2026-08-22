package com.ratnesh.connecthub.notificationservice.service;

import com.ratnesh.connecthub.commonlib.event.*;

public interface NotificationService {
    void handlePostCreated(PostCreatedEvent event);

    void handlePostLiked(PostLikedEvent event);

    void handlePostReposted(PostRepostedEvent event);

    void handleConnectionRequestSent(ConnectionRequestSentEvent event);

    void handleConnectionRequestAccepted(ConnectionRequestAcceptedEvent event);
}
