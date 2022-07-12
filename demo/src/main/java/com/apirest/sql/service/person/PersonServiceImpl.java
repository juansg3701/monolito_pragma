package com.apirest.sql.service.person;

import com.apirest.sql.dao.person.PersonDAO;
import com.apirest.sql.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return person;
    }

    @Override
    public void save(Person person) {
        personDAO.save(person);
    }

    @Override
    public void update(Person person) {
        personDAO.update(person);
    }

    @Override
    public void deleteById(int id) {
        personDAO.delete(id);
    }
}
