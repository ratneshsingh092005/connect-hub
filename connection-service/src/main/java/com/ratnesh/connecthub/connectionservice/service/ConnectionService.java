package com.ratnesh.connecthub.connectionservice.service;

import com.ratnesh.connecthub.connectionservice.dto.PersonDto;

import java.util.List;

public interface ConnectionService {
    List<PersonDto> getFirstDegreeConnections(Long userId);

    List<PersonDto> getMyFirstDegreeConnections();

    List<PersonDto> getSecondDegreeConnections();

    List<PersonDto> getPendingRequests();

    List<PersonDto> getSentRequests();

    void sendConnectionRequest(Long receiverId);

    void acceptConnectionRequest(Long senderId);

    void rejectConnectionRequest(Long senderId);

    void removeConnection(Long connectionId);
}
