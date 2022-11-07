package com.apirest.sql.service.image;

import com.apirest.sql.entity.Image;

import java.util.List;

public interface ImageService {
    public List<Image> findAll();
    public Image findById(int id);
    public Image save(Image Image);
    //public boolean saveImage(MultipartFile image);
    public Image update(Image Image);
    public boolean deleteById(int id);
}
