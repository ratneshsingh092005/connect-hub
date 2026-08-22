package com.ratnesh.connecthub.connectionservice.consumer;


import com.ratnesh.connecthub.commonlib.event.UserCreatedEvent;
import com.ratnesh.connecthub.connectionservice.repository.PersonRepository;
import com.ratnesh.connecthub.connectionservice.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserConsumer {

    private final PersonService personService;
    private final PersonRepository personRepository;

    @KafkaListener(topics ="user-created")
    public void handleUserCreated(UserCreatedEvent userCreated){

        log.info("handlePersonCreated: {}",userCreated);
        personService.createPerson(userCreated.getUserId(), userCreated.getName());

    }


}
