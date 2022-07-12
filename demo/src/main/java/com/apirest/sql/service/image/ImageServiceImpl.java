package com.apirest.sql.service.image;


import com.apirest.sql.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Repository;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
        return Image;
    }

    @Override
    public void save(Image image) {
        ImageDAO.save(image);
    }

    /*
    @Override
    public boolean saveImage(MultipartFile image) {
        boolean state_returnt = false;
        try{
            Files.copy(image.getInputStream(), this.root.resolve(image.getOriginalFilename()));
            state_returnt = true;
        }catch(Exception e){
            throw new RuntimeException("No se puede guardar la imagen" + e.getMessage());
        }
        return state_returnt;
    }
    */


    public Resource load(String filename){
        try{
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }else{
                throw new RuntimeException("No se pudo leer el archivo");
            }
        }catch(MalformedURLException e){
            throw new RuntimeException("Error: "  + e.getMessage());
        }
    }

    @Override
    public void update(Image Image) {
        ImageDAO.update(Image);
    }

    @Override
    public void deleteById(int id) {
        ImageDAO.delete(id);
    }
}
