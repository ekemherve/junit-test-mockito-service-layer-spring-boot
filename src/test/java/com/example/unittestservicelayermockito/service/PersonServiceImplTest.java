package com.example.unittestservicelayermockito.service;

import com.example.unittestservicelayermockito.dataaccess.entity.PersonEntity;
import com.example.unittestservicelayermockito.dataaccess.mapper.PersonMapper;
import com.example.unittestservicelayermockito.dataaccess.repository.PersonRepository;
import com.example.unittestservicelayermockito.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonMapper personMapper;

    //Mockito cannot instantiate an interface so to you use @InjectMocks with an interface you must instantiate
    //Manually your interface like the example below with personService.
    //U could have get rid of the manual instantiation if you use personServiceImpl and not the interface;
    //Is this a good practice ????
    @InjectMocks
    private PersonService personService = new PersonServiceImpl(personRepository, personMapper);

    private PersonEntity firstEntityToSave;

    private Person firstModelToSave;

    private static final String USERNAME_IORI = "iori";
    private static final String PASSWORD_IORI = "iori";
    private static final String EMAIL_IORI = "iori@gmail.com";
    private static final Long ID = 1L;

    @BeforeEach
    public void setUp(){
        firstEntityToSave = new PersonEntity(USERNAME_IORI, PASSWORD_IORI, EMAIL_IORI);
        firstModelToSave = new Person(USERNAME_IORI, PASSWORD_IORI, EMAIL_IORI);
        System.out.println(personService);
    }

    @Test
    public void init(){

        assertAll(
                () -> assertNotNull(personService),
                () -> assertNotNull(personRepository),
                () -> assertNotNull(personMapper)
        );
    }

    @Test
    public void saveNullModelThrowsIllegalArgumentException(){

        assertThrows(IllegalArgumentException.class, () -> personService.save(null));
    }

    @Test
    public void saveExistingModelThrowsException(){

        PersonEntity existingEntity = new PersonEntity(firstEntityToSave);
        existingEntity.setId(ID);
        when(personRepository.findByUsernameOrEmail(USERNAME_IORI, EMAIL_IORI)).thenReturn(Collections.singletonList(existingEntity));
        assertAll(
                () -> assertNotNull(personRepository.findByUsernameOrEmail(USERNAME_IORI, EMAIL_IORI)),
                () ->assertEquals(1, personRepository.findByUsernameOrEmail(USERNAME_IORI, EMAIL_IORI).size()),
                () ->assertThrows(Exception.class, () -> personService.save(firstModelToSave))
        );
    }


}
