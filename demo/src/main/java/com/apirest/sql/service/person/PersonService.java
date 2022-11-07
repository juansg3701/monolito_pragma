package com.apirest.sql.service.person;

import com.apirest.sql.entity.Person;

import java.util.List;

public interface PersonService {
    public List<Person> findAll();
    public Person findById(int id);
    public Person save(Person person);
    public Person update(Person person);
    public boolean deleteById(int id);
}
