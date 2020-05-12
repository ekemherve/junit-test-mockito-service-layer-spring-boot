package com.example.unittestservicelayermockito.service;

import com.example.unittestservicelayermockito.dataaccess.entity.PersonEntity;
import com.example.unittestservicelayermockito.dataaccess.mapper.PersonMapper;
import com.example.unittestservicelayermockito.dataaccess.repository.PersonRepository;
import com.example.unittestservicelayermockito.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    //If the two fields are final unit test using only mockito wont work
    private PersonRepository personRepository;
    private PersonMapper personMapper;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    @Override
    public List<Person> findAll() {
        return personMapper.mapToBOS(personRepository.findAll());
    }

    @Override
    public Person save(Person person) throws Exception {

        if(Objects.isNull(person)){
            throw new IllegalArgumentException("PERSON CANNOT BE NULL");
        }

        List<PersonEntity> persons = personRepository.findByUsernameOrEmail(person.getUsername(), person.getEmail());
        if(!persons.isEmpty()){
            throw new Exception("USERNAME OR EMAIL ALREADY EXITS");
        }

        PersonEntity entityToSave = personMapper.mapToEO(person);
        entityToSave = personRepository.save(entityToSave);
        return personMapper.mapToBO(entityToSave);
    }


    @Override
    public void delete(Long id) {

        Optional<PersonEntity> optional = personRepository.findById(id);
        optional.ifPresent(personRepository::delete);
    }
}
