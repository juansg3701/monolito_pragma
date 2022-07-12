package com.apirest.sql.service.imagemongo;

import com.apirest.sql.entity.ImageMongo;

import java.util.List;
import java.util.Optional;

public interface ImageMongoService {
    public List<ImageMongo> findAll();
    public Optional<ImageMongo> findById(String id);
    public List<ImageMongo> findListByPerson(int id);
    //public Optional<ImageMongo> findByName(String name);
    public ImageMongo save(ImageMongo image);
    public void update(String id, ImageMongo image);
    public void delete(String id);
}
