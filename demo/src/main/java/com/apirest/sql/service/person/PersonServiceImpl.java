package com.apirest.sql.service.person;

import com.apirest.sql.dao.person.PersonDAO;
import com.apirest.sql.entity.Person;
import com.apirest.sql.exception.EmptyInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

//Repository para saber que es un DAO
@Repository
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Override
    public List<Person> findAll() {
        List<Person> listPersons = personDAO.findAll();
        return listPersons;
    }

    @Override
    public Person findById(int id) {
        Person person = personDAO.findById(id);
        if(person==null){
            throw new NoSuchElementException();
        }

        return person;

    }

    @Override
    public Person save(Person person) {

        if(person.getCedula().isEmpty() ||
                person.getName().isEmpty() ||
                person.getAge()<18 ||
                person.getEmail().isEmpty() ||
                person==null) {
            throw new EmptyInputException();
        }
        personDAO.save(person);
        return person;
    }

    @Override
    public Person update(Person person) {
        Person person_send = new Person();
        try {
            personDAO.update(person);
            return person;
        }catch (Exception e) {
            return person_send;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            personDAO.delete(id);
            return true;
        }catch (Exception e) {
            return false;
        }

    }
}
