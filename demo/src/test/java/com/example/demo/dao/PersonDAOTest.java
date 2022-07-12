package com.example.demo.dao;

import com.apirest.sql.dao.person.PersonDAOImpl;
import com.apirest.sql.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
public class PersonDAOTest {
    @Autowired
    private PersonDAOImpl person;

    @Test
    public void whenFindAll_thenReturnListPersons(){

    }
    @Test
    public void testFindAll(){
        List<Person> resultado = person.findAll();
        String esperado = "esperado";
        assertEquals(esperado,resultado);
    }
}
