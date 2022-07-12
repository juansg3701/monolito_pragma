package com.apirest.sql.service.person;

import com.apirest.sql.entity.Person;

import java.util.List;

public interface PersonService {
    public List<Person> findAll();
    public Person findById(int id);
    public void save(Person person);
    public void update(Person person);
    public void deleteById(int id);
}
