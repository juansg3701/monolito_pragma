package com.apirest.sql.controller;

import com.apirest.sql.entity.Person;
import com.apirest.sql.service.person.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonRestControllerTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonRestController controller;

    private Person person;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        person = new Person();
        person.setName("Juan");
        person.setEmail("juan@gmail.com");
        person.setId(123);

    }

    @Test
    void findAll() {
        when(personService.findAll()).thenReturn(Arrays.asList(person));
        List<Person> result= controller.findAll();
        assertEquals(person, result.get(0));
        assertNotNull(controller.findAll());
    }

    @Test
    void getPerson() {
        when(personService.findById(person.getId())).thenReturn(person);
        var result= controller.getPerson(person.getId());
        assertEquals(person, result);
        assertNotNull(controller.getPerson(person.getId()));
    }

    @Test
    void addPerson() {
        when(personService.save(person)).thenReturn(person);
        var result= controller.addPerson(person);
        assertEquals(person, result);
        assertNotNull(controller.addPerson(person));
    }

    @Test
    void updatePerson() {
        when(personService.update(person)).thenReturn(person);
        var result= controller.updatePerson(person);
        assertEquals(person, result);
        assertNotNull(controller.updatePerson(person));
    }

    @Test
    void deletePerson() {
        System.out.println(personService.deleteById(person.getId()));
        when(personService.deleteById(person.getId())).thenReturn(true);
        var result = controller.deletePerson(person.getId());
        System.out.println(result);

        //assertNotNull(controller.deletePerson(result.getId()));
    }
}