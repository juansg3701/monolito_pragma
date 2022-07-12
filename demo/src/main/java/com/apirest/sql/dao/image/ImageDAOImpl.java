package com.apirest.sql.dao.image;

import com.apirest.sql.entity.Image;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ImageDAOImpl implements ImageDAO {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Image> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Image> theQuery = currentSession.createQuery("from Image", Image.class);
        List<Image> Images = theQuery.getResultList();
        return Images;
    }

    @Override
    public Image findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Image Image = currentSession.get(Image.class, id);
        return Image;
    }

    @Override
    public void save(Image Image) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.save(Image);
    }

    @Override
    @Transactional
    public void update(Image Image) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.update(Image);
    }

    @Override
    @Transactional
    public void delete(int id ) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Image> theQuery = currentSession.createQuery("delete from Image where id=:idImage");
        theQuery.setParameter("idImage", id);
        theQuery.executeUpdate ();
    }
}
