package com.apirest.sql.controller;

import com.apirest.sql.dao.imagemongo.ImageMongoDAO;
import com.apirest.sql.entity.ImageMongo;
import com.apirest.sql.entity.Person;
import com.apirest.sql.service.imagemongo.ImageMongoService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ImageMongoRestController {
    @Autowired
    private ImageMongoService imageMongoService;

    @Autowired(required = false)
    private ImageMongoDAO imageMongoDAO;

    @Autowired
    private PersonRestController personRestController;

    @GetMapping("/imagesmongo")
    public List<ImageMongo> getImagesgetImagesMongo(String url) {
       return imageMongoService.findAll();
    }

    @GetMapping("/imagesmongo/{ImageId}")
    public Optional<ImageMongo> getImage(@PathVariable String ImageId) {
        return imageMongoService.findById(ImageId);
    }

    @PostMapping("/imagesmongo/{PersonaId}")
    public ImageMongo addImage(@PathVariable int PersonaId, @RequestParam("file") MultipartFile imagen) {
        ImageMongo image = new ImageMongo();
        Person informarcion_persona = personRestController.getPerson(PersonaId);
        if(informarcion_persona.getImages().size() > 0){
            List vacio = new ArrayList<>();
            informarcion_persona.setImages(vacio);
        }
        String nombre_hora_unix = Long.toString(System.currentTimeMillis());
        String extension_archivo = FilenameUtils.getExtension(imagen.getOriginalFilename());
        String nombre_imagen = nombre_hora_unix + "." + extension_archivo;
        Timestamp fecha_hora_actual = new Timestamp(System.currentTimeMillis());

        if(informarcion_persona!=null) {
            image.set_person(informarcion_persona);

            if(!imagen.isEmpty()){
                Path directorioImagenes = Paths.get("demo/src/main/resources/images");
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

                try {
                    byte[] byteImg = imagen.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta+"/"+nombre_imagen);
                    Files.write(rutaCompleta, byteImg);

                    image.setExtension(extension_archivo);
                    image.setName(nombre_hora_unix);
                    image.setUrl(rutaCompleta.toString());
                    image.setCreatedAt(fecha_hora_actual);
                    image.setUpdatedAt(fecha_hora_actual);
                } catch (IOException e) {
                    throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
                }
            }
            imageMongoService.save(image);
        }
        return image;
    }

    @PutMapping("/imagesmongo/{ImageId}")
    public ImageMongo updateImage(@PathVariable String ImageId, @RequestParam("file") MultipartFile image){
        ImageMongo imagen_encontrada = imageMongoService.findById(ImageId).get();

        String extension_archivo = FilenameUtils.getExtension(image.getOriginalFilename());
        String nombre_imagen = imagen_encontrada.getName() + "." + extension_archivo;
        Timestamp fecha_hora_actual = new Timestamp(System.currentTimeMillis());

            if(!image.isEmpty()){
                Path directorioImagenes = Paths.get("demo/src/main/resources/images");
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

                try {
                    byte[] byteImg = image.getBytes();
                    Path rutaCompleta = Paths.get(rutaAbsoluta+"/"+nombre_imagen);
                    Files.write(rutaCompleta, byteImg);

                    imagen_encontrada.setExtension(extension_archivo);
                    imagen_encontrada.setUrl(rutaCompleta.toString());
                    imagen_encontrada.setUpdatedAt(fecha_hora_actual);
                } catch (IOException e) {
                    throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
                }
                imageMongoService.save(imagen_encontrada);
            }


        return imagen_encontrada;

    }

    @DeleteMapping("/imagesmongo/{ImageId}")
    public String deleteImage(@PathVariable String ImageId){
        ImageMongo imageMongo  = imageMongoService.findById(ImageId).get();

        if(imageMongo==null){
            throw new RuntimeException("Imagen no encontrada - " + ImageId);
        }

        try{
            Path rutaCompleta = Paths.get("demo/src/main/resources/images");
            Path ruta_eliminar_imagen = Paths.get(rutaCompleta+"/"+imageMongo.getName()+"."+imageMongo.getExtension());
            Files.delete(ruta_eliminar_imagen);
        }catch (IOException e) {
            throw new RuntimeException("Error al eliminar la imagen: " + e.getMessage());
        }
        imageMongoService.delete(ImageId);

        return "Imagen eliminada con id:" + ImageId;
    }
}
