package com.ratnesh.connecthub.notificationservice.dto;

import java.time.Instant;

public record NotificationResponse(
        Long id,
        Long userId,
        Long referenceId,
        String message,
        boolean read,
        Instant createdAt
) {}
