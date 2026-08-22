package com.ratnesh.connecthub.connectionservice.service;


import com.ratnesh.connecthub.connectionservice.entity.Person;
import com.ratnesh.connecthub.connectionservice.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public void createPerson(Long userId,String name){
        Person person = Person.builder()
                .userId(userId)
                .name(name)
                .build();

        personRepository.save(person);
    }

}
