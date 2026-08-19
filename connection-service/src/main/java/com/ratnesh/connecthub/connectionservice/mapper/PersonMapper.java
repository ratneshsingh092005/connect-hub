package com.ratnesh.connecthub.connectionservice.mapper;


import com.ratnesh.connecthub.connectionservice.dto.PersonDto;
import com.ratnesh.connecthub.connectionservice.entity.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {


    List<PersonDto> toPersonDtoList(List<Person> firstDegreeConnections);
}
