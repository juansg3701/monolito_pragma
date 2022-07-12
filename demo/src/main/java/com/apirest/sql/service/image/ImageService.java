package com.apirest.sql.service.image;

import com.apirest.sql.entity.Image;

import java.util.List;

public interface ImageService {
    public List<Image> findAll();
    public Image findById(int id);
    public void save(Image Image);
    //public boolean saveImage(MultipartFile image);
    public void update(Image Image);
    public void deleteById(int id);
}
