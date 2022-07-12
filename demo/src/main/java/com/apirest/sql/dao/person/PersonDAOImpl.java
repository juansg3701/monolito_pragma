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
    public void save(Person person) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(person);
    }

    @Override
    @Transactional
    public void update(Person person) {
        Session currentSession = entityManager.unwrap(Session.class);
        //currentSession.update(person);
        currentSession.update(person);
    }

    @Override
    @Transactional
    public void delete(int id ) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Person> theQuery = currentSession.createQuery("delete from Person where id=:idPerson");
        theQuery.setParameter("idPerson", id);
        theQuery.executeUpdate ();
    }
}
