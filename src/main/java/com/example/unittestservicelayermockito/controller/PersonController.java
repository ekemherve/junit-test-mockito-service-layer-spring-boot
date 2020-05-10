package com.example.unittestservicelayermockito.controller;

import com.example.unittestservicelayermockito.model.Person;
import com.example.unittestservicelayermockito.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(personService.findAll());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Person person){

        try {
            return ResponseEntity.ok(personService.save(person));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
