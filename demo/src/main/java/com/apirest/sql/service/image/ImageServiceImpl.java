package com.apirest.sql.service.image;


import com.apirest.sql.entity.Image;
import com.apirest.sql.exception.EmptyInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ImageServiceImpl implements ImageService {
    @Autowired
    private com.apirest.sql.dao.image.ImageDAO ImageDAO;

    private final Path root = Paths.get("images");

    @Override
    public List<Image> findAll() {
        List<Image> listImages = ImageDAO.findAll();
        return listImages;
    }

    @Override
    public Image findById(int id) {
        Image Image = ImageDAO.findById(id);
        if(Image==null){
            throw new NoSuchElementException();
        }
        return Image;
    }

    @Override
    public Image save(Image image) {

        if(image.getExtension().isEmpty() ||
                image.getExtension().isEmpty() ||
                image.getUrl().isEmpty() ||
                image==null) {
            throw new EmptyInputException();
        }
            ImageDAO.save(image);
            return image;

    }

    @Override
    public Image update(Image Image) {
        Image image_send = new Image();
        try {
            ImageDAO.update(Image);
            return Image;
        }catch (Exception e) {
            return image_send;
        }
    }

    @Override
    public boolean deleteById(int id) {
        try {
            ImageDAO.delete(id);
            return true;
        }catch (Exception e) {
            return false;
        }

    }
}
