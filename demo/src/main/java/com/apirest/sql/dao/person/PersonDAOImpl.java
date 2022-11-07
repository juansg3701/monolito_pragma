package com.apirest.sql.dao.person;

import com.apirest.sql.entity.Person;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class PersonDAOImpl implements PersonDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Person> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Person> theQuery = currentSession.createQuery("from Person", Person.class);
        List<Person> persons = theQuery.getResultList();
        return persons;
    }

    @Override
    public Person findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Person person = currentSession.get(Person.class, id);
        return person;
    }

    @Override
    public Person save(Person person) {
        Session currentSession = entityManager.unwrap(Session.class);
        Person person_return = new Person();
        try {
            currentSession.save(person);
            return person;
        }catch (Exception e) {
            return person_return;
        }

    }

    @Override
    @Transactional
    public Person update(Person person) {
        Session currentSession = entityManager.unwrap(Session.class);
        Person person_return = new Person();
        try {
            currentSession.update(person);
            return person;
        }catch (Exception e){
            return person_return;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id ) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Person> theQuery = currentSession.createQuery("delete from Person where id=:idPerson");
        try{
            theQuery.setParameter("idPerson", id);
            theQuery.executeUpdate ();
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
