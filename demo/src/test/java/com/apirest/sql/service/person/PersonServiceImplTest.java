package com.apirest.sql.service.person;

import com.apirest.sql.dao.person.PersonDAO;
import com.apirest.sql.entity.Person;
import com.apirest.sql.entity.TypeDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonServiceImplTest {

    @Mock
    private PersonDAO personDAO;

    @InjectMocks
    private PersonServiceImpl personService;

    private Person person;
    private Person person_actualizada;
    private TypeDocument type_document;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        type_document = new TypeDocument();
        type_document.setType("cedula");
        type_document.setId(1);

        person = new Person();
        person.setName("Juan");
        person.setCedula("112323");
        person.setEmail("juan@gmail.com");
        person.setType_document(type_document);
        person.setAge(23);
        person.setId(123);

        person_actualizada = new Person();
        person_actualizada.setName("Juan actualizada");
        person_actualizada.setEmail("juan@gmail.com");
        person_actualizada.setId(123);
    }

    @Test
    void findAll() {
        when(personDAO.findAll()).thenReturn(Arrays.asList(person));
        List<Person> result= personService.findAll();
        assertEquals(person, result.get(0));
        assertNotNull(personService.findAll());
    }

    @Test
    void findById() {
        when(personDAO.findById(person.getId())).thenReturn(person);
        var result= personService.findById(person.getId());
        assertEquals(person, result);
        assertNotNull(personService.findById(person.getId()));
    }

    @Test
    void save() {
        when(personDAO.save(person)).thenReturn(person);
        var result= personService.save(person);
        assertEquals(person, result);
        assertNotNull(personService.save(person));
    }

    @Test
    void update() {
        when(personDAO.update(person_actualizada)).thenReturn(person_actualizada);
        var result= personService.update(person);
        assertEquals(person, result);
        assertNotNull(personService.update(person));
    }

    @Test
    void deleteById() {
        when(personDAO.delete(person.getId())).thenReturn(true);
        assertTrue(personService.deleteById(person.getId()));
    }
}