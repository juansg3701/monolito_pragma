package com.apirest.sql.service.imagemongo;

import com.apirest.sql.dao.imagemongo.ImageMongoDAO;
import com.apirest.sql.entity.ImageMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ImageMongoServiceImpl implements ImageMongoService {
    @Autowired(required = false)
    private ImageMongoDAO imageMongoDAO;

    @Override
    public List<ImageMongo> findAll() {
        List<ImageMongo> array_imprimir = new ArrayList<>();
        imageMongoDAO.findAll().forEach(item -> array_imprimir.add(item));
        return array_imprimir;
    }

    @Override
    public Optional<ImageMongo> findById(String id) {
        Optional<ImageMongo> image_imprimir =  imageMongoDAO.findById(id);
        if(image_imprimir==null){
            throw new NoSuchElementException();
        }
        return image_imprimir;
    }

    @Override
    public List<ImageMongo> findListByPerson(int personId) {
        return imageMongoDAO.findAllByPerson(personId);
    }

    @Override
    public ImageMongo save(ImageMongo image) {
        return imageMongoDAO.save(image);
    }

    @Override
    public ImageMongo update(ImageMongo image) {
        return imageMongoDAO.save(image);
    }

    @Override
    public boolean delete(String id) {
        try {
            imageMongoDAO.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
