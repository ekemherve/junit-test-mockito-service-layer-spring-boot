package com.example.unittestservicelayermockito.dataaccess.mapper;

import com.example.unittestservicelayermockito.dataaccess.entity.PersonEntity;
import com.example.unittestservicelayermockito.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person mapToBO(PersonEntity person);
    PersonEntity mapToEO(Person person);
    List<Person> mapToBOS(List<PersonEntity> persons);
    List<PersonEntity> mapToEOS(List<Person> persons);

}
