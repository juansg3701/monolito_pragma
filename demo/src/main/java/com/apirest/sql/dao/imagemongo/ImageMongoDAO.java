package com.apirest.sql.dao.imagemongo;

import com.apirest.sql.entity.ImageMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

//extends MongoRepository<ImageMongo, String>
public interface ImageMongoDAO extends MongoRepository<ImageMongo, String>{
    /*
    public List<ImageMongo> findAll();
    public ImageMongo findById(String id);
    public void save(ImageMongo image);
    public void update(String id, ImageMongo image);
    public void delete(String id);

     */
    @Query("_person:?0")
    public List<ImageMongo> findAllByPerson(int personId);


    public long count();
}
