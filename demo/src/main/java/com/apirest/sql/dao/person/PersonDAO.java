package com.apirest.sql.dao.person;

import com.apirest.sql.entity.Person;

import java.util.List;

//extends JpaRepository<Person, Long>
public interface PersonDAO  {
    public List<Person> findAll();
    public Person findById(int id);
    public Person save(Person person);
    public Person update(Person person);
    public boolean delete(int id);

    /*
    @Query("SELECT p FROM person p WHERE "+
            "p.name LIKE CONCAT('%',:query,'%'")
    List<Person> getAgePersons(String query);
     */
}
