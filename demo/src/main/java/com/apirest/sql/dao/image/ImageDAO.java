package com.apirest.sql.dao.image;

import com.apirest.sql.entity.Image;

import java.util.List;

public interface ImageDAO {
    public List<Image> findAll();
    public Image findById(int id);
    public void save(Image Image);
    public void update(Image Image);
    public void delete(int id);
}
