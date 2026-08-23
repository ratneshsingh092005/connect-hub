package com.ratnesh.connecthub.notificationservice.service;

import com.ratnesh.connecthub.commonlib.error.ResourceNotFoundException;
import com.ratnesh.connecthub.commonlib.event.*;
import com.ratnesh.connecthub.commonlib.security.AuthUtil;
import com.ratnesh.connecthub.notificationservice.client.ConnectionsServiceClient;
import com.ratnesh.connecthub.notificationservice.dto.NotificationResponse;
import com.ratnesh.connecthub.notificationservice.dto.PersonDto;
import com.ratnesh.connecthub.notificationservice.entity.Notification;
import com.ratnesh.connecthub.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ConnectionsServiceClient connectionsServiceClient;
    private final AuthUtil authUtil;
    @Override
    public void handlePostCreated(PostCreatedEvent event) {

        List<PersonDto> connections =
                connectionsServiceClient
                        .getFirstDegreeConnections(event.getOwnerUserId());

        for (PersonDto connection : connections) {

            Notification notification = Notification.builder()
                    .userId(connection.userId())
                    .message("User with id: " + event.getOwnerUserId()
                            + " created a new post")
                    .referenceId(event.getPostId())
                    .build();

            notificationRepository.save(notification);
        }
    }

    @Override
    public void handlePostLiked(PostLikedEvent event) {

        Notification notification = Notification.builder()
                .userId(event.getPostOwnerId())
                .message("User with id: " + event.getLikedByUserId()
                        + " liked your post")
                .referenceId(event.getPostId())
                .build();

        notificationRepository.save(notification);
    }

    @Override
    public void handlePostReposted(PostRepostedEvent event) {

        Notification notification = Notification.builder()
                .userId(event.getPostOwnerId())
                .message("User with id: " + event.getRepostedByUserId()
                        + " reposted your post")
                .referenceId(event.getPostId())
                .build();

        notificationRepository.save(notification);
    }

    @Override
    public void handleConnectionRequestSent(ConnectionRequestSentEvent event) {

        Notification notification = Notification.builder()
                .userId(event.getReceiverUserId())
                .message("User with id: " + event.getSenderUserId()
                        + " sent you a connection request")
                .build();

        notificationRepository.save(notification);
    }

    @Override
    public void handleConnectionRequestAccepted(ConnectionRequestAcceptedEvent event) {

        Notification notification = Notification.builder()
                .userId(event.getSenderUserId())
                .message("User with id: " + event.getReceiverUserId()
                        + " accepted your connection request")
                .build();

        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationResponse> getNotifications() {
        Long userId = authUtil.getCurrentUserId();
        return notificationRepository.findByUserId(userId)
                .stream()
                .map(notification -> new NotificationResponse(
                        notification.getId(),
                        notification.getUserId(),
                        notification.getReferenceId(),
                        notification.getMessage(),
                        notification.isRead(),
                        notification.getCreatedAt()
                ))
                .toList();
    }

    @Override
    public NotificationResponse markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification",id.toString()));

        notification.setRead(true);

        Notification saved = notificationRepository.save(notification);

        return new NotificationResponse(
                saved.getId(),
                saved.getUserId(),
                saved.getReferenceId(),
                saved.getMessage(),
                saved.isRead(),
                saved.getCreatedAt()
        );
    }

    @Override
    public void markAllAsRead() {
        Long userId = authUtil.getCurrentUserId();

        List<Notification> notifications =
                notificationRepository.findByUserId(userId);

        notifications.forEach(notification -> notification.setRead(true));

        notificationRepository.saveAll(notifications);
    }

}