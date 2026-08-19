package com.ratnesh.connecthub.connectionservice.service;


import com.ratnesh.connecthub.commonlib.error.BadRequestException;
import com.ratnesh.connecthub.commonlib.error.ResourceNotFoundException;
import com.ratnesh.connecthub.commonlib.security.AuthUtil;
import com.ratnesh.connecthub.connectionservice.dto.PersonDto;
import com.ratnesh.connecthub.connectionservice.mapper.PersonMapper;
import com.ratnesh.connecthub.connectionservice.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final AuthUtil authUtil;

    public List<PersonDto> getFirstDegreeConnections(Long userId) {
        return personMapper.toPersonDtoList(
                personRepository.getFirstDegreeConnections(userId)
        );
    }

    public List<PersonDto> getMyFirstDegreeConnections() {

        Long userId = authUtil.getCurrentUserId() ;

        return personMapper.toPersonDtoList(
                personRepository.getFirstDegreeConnections(userId)
        );
    }

    public List<PersonDto> getSecondDegreeConnections() {

        Long userId = authUtil.getCurrentUserId() ;

        return personMapper.toPersonDtoList(
                personRepository.getSecondDegreeConnections(userId)
        );
    }

    public List<PersonDto> getPendingRequests() {

        Long userId = authUtil.getCurrentUserId() ;

        return personMapper.toPersonDtoList(
                personRepository.getPendingRequests(userId)
        );
    }

    public List<PersonDto> getSentRequests() {

        Long userId = authUtil.getCurrentUserId() ;

        return personMapper.toPersonDtoList(
                personRepository.getSentRequests(userId)
        );
    }

    public void sendConnectionRequest(Long receiverUserId) {

        Long senderUserId = authUtil.getCurrentUserId() ;

        if (senderUserId.equals(receiverUserId)) {
            throw new BadRequestException("You cannot send a connection request to yourself.");
        }

        personRepository.findByUserId(senderUserId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Person", senderUserId.toString()));

        personRepository.findByUserId(receiverUserId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Person", receiverUserId.toString()));

        if (personRepository.alreadyConnected(senderUserId, receiverUserId)) {
            throw new BadRequestException("Users are already connected.");
        }

        if (personRepository.connectionRequestExists(senderUserId, receiverUserId)
                || personRepository.connectionRequestExists(receiverUserId, senderUserId)) {
            throw new BadRequestException("A connection request already exists between these users.");
        }

        personRepository.sendConnectionRequest(senderUserId, receiverUserId);


    }

    public void acceptConnectionRequest(Long senderUserId) {

        Long receiverUserId = authUtil.getCurrentUserId();

        if (!personRepository.connectionRequestExists(senderUserId, receiverUserId)) {
            throw new BadRequestException("No pending connection request found.");
        }

        personRepository.acceptConnectionRequest(senderUserId, receiverUserId);


    }

    public void rejectConnectionRequest(Long senderUserId) {

        Long receiverUserId = authUtil.getCurrentUserId();

        if (!personRepository.connectionRequestExists(senderUserId, receiverUserId)) {
            throw new BadRequestException("No pending connection request found.");
        }

        personRepository.rejectConnectionRequest(senderUserId, receiverUserId);
    }

    public void removeConnection(Long connectionUserId) {

        Long userId = authUtil.getCurrentUserId();

        if (!personRepository.alreadyConnected(userId, connectionUserId)) {
            throw new BadRequestException("Users are not connected.");
        }

        personRepository.removeConnection(userId, connectionUserId);
    }
}