package com.ratnesh.connecthub.notificationservice.consumer;

import com.ratnesh.connecthub.commonlib.event.ConnectionRequestAcceptedEvent;
import com.ratnesh.connecthub.commonlib.event.ConnectionRequestSentEvent;
import com.ratnesh.connecthub.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConnectionEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "connection-request-sent", groupId = "notification-service")
    public void consumeConnectionRequestSent(ConnectionRequestSentEvent event) {
        notificationService.handleConnectionRequestSent(event);
    }

    @KafkaListener(topics = "connection-request-accepted", groupId = "notification-service")
    public void consumeConnectionRequestAccepted(ConnectionRequestAcceptedEvent event) {
        notificationService.handleConnectionRequestAccepted(event);
    }
}