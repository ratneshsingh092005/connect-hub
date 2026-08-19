package com.ratnesh.connecthub.connectionservice.controller;

import com.ratnesh.connecthub.connectionservice.dto.PersonDto;
import com.ratnesh.connecthub.connectionservice.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/{userId}/first-degree")
    public ResponseEntity<List<PersonDto>> getFirstDegreeConnections(@PathVariable Long userId) {
        return ResponseEntity.ok(connectionService.getFirstDegreeConnections(userId));
    }

    @GetMapping("/first-degree")
    public ResponseEntity<List<PersonDto>> getMyFirstDegreeConnections() {
        return ResponseEntity.ok(connectionService.getMyFirstDegreeConnections());
    }

    @GetMapping("/second-degree")
    public ResponseEntity<List<PersonDto>> getSecondDegreeConnections() {
        return ResponseEntity.ok(connectionService.getSecondDegreeConnections());
    }

    @GetMapping("/pending")
    public ResponseEntity<List<PersonDto>> getPendingRequests() {
        return ResponseEntity.ok(connectionService.getPendingRequests());
    }

    @GetMapping("/sent")
    public ResponseEntity<List<PersonDto>> getSentRequests() {
        return ResponseEntity.ok(connectionService.getSentRequests());
    }

    @PostMapping("/request/{receiverId}")
    public ResponseEntity<Void> sendRequest(@PathVariable Long receiverId) {
        connectionService.sendConnectionRequest(receiverId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/accept/{senderId}")
    public ResponseEntity<Void> acceptRequest(@PathVariable Long senderId) {
        connectionService.acceptConnectionRequest(senderId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reject/{senderId}")
    public ResponseEntity<Void> rejectRequest(@PathVariable Long senderId) {
        connectionService.rejectConnectionRequest(senderId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{connectionId}")
    public ResponseEntity<Void> removeConnection(@PathVariable Long connectionId) {
        connectionService.removeConnection(connectionId);
        return ResponseEntity.ok().build();
    }
}