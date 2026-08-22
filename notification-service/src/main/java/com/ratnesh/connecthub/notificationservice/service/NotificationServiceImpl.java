package com.ratnesh.connecthub.notificationservice.service;

import com.ratnesh.connecthub.commonlib.event.*;
import com.ratnesh.connecthub.notificationservice.client.ConnectionsServiceClient;
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
                .build();

        notificationRepository.save(notification);
    }

    @Override
    public void handlePostReposted(PostRepostedEvent event) {

        Notification notification = Notification.builder()
                .userId(event.getPostOwnerId())
                .message("User with id: " + event.getRepostedByUserId()
                        + " reposted your post")
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
}