package com.example.unittestservicelayermockito.dataaccess.repository;

import com.example.unittestservicelayermockito.dataaccess.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    PersonEntity findByUsername(String username);
    PersonEntity findByEmail(String email);
    List<PersonEntity> findByUsernameOrEmail(String username, String email);
}
