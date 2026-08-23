package com.ratnesh.connecthub.notificationservice.controller;

import com.ratnesh.connecthub.notificationservice.dto.NotificationResponse;
import com.ratnesh.connecthub.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/all")
    public List<NotificationResponse> getNotifications() {
        return notificationService.getNotifications();
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
         notificationService.markAsRead(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/read-all")
    public ResponseEntity<Void> markAllAsRead() {
        notificationService.markAllAsRead();
        return ResponseEntity.noContent().build();
    }

}
