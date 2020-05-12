package com.example.unittestservicelayermockito.dataaccess.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min = 4, max = 64)
    private String username;

    @Column(nullable = false)
    @Size(min = 4, max = 64)
    private String password;

    @Column(unique = true, nullable = false)
    @Size(min = 4, max = 64)
    private String email;

    public PersonEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public PersonEntity(PersonEntity personEntity){
        this(personEntity.id, personEntity.getUsername(), personEntity.getPassword(), personEntity.getEmail());
    }
}
