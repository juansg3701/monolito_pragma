package com.apirest.sql.controller;


import com.apirest.sql.entity.Image;
import com.apirest.sql.entity.Person;
import com.apirest.sql.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
public class ImageRestController {
    @Autowired
    private ImageService imageservice;

    @Autowired
    private PersonRestController personRestController;

    @GetMapping("/images")
    public List<Image> findAll() {
        return imageservice.findAll();
    }

    @GetMapping("/images/{ImageId}")
    public Image getImage(@PathVariable int ImageId) {
        Image image = imageservice.findById(ImageId);
        return image;
    }

    @PostMapping("/images/{PersonaId}")
    //, @RequestBody Image image @RequestParam("file") MultipartFile imagen
    public Image addImage(@PathVariable int PersonaId, @RequestParam("file") MultipartFile imagen) {
        Image image = new Image();
        image.setId(0);
        Person informarcion_persona = personRestController.getPerson(PersonaId);
        String nombre_hora_unix = Long.toString(System.currentTimeMillis());
        String extension_archivo = FilenameUtils.getExtension(imagen.getOriginalFilename());
        String nombre_imagen = nombre_hora_unix + "." + extension_archivo;

        if(informarcion_persona!=null) {
            image.setPerson(informarcion_persona);

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
                } catch (IOException e) {
                    throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
                }
            }
            imageservice.save(image);
        }
        return image;
    }


    @PutMapping("/images/{ImageId}")
    //@RequestBody Image image
    public Image updateImage(@PathVariable int ImageId, @RequestParam("file") MultipartFile imagen) {
        Image image = imageservice.findById(ImageId);

        String extension_archivo = FilenameUtils.getExtension(imagen.getOriginalFilename());
        String nombre_imagen = image.getName()+"."+ extension_archivo;

        if(!imagen.isEmpty() && image!=null) {
            Path directorioImagenes = Paths.get("demo/src/main/resources/images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] byteImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta+"/"+nombre_imagen);
                //Path ruta_borrar_imagen = Paths.get(rutaAbsoluta+"/"+imagen.getName());
                //System.out.println(ruta_borrar_imagen);

                //Files.delete(ruta_borrar_imagen);
                Files.write(rutaCompleta, byteImg);

                image.setExtension(extension_archivo);
                image.setUrl(rutaCompleta.toString());
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
            }
            imageservice.update(image);
        }
        return image;
    }

    @DeleteMapping("/images/{id}")
    public String deleteImage(@PathVariable int id) {
        Image image = imageservice.findById(id);

        if(image == null){
            throw new RuntimeException("Imagen no encontrada - " + id);
        }
        try{
            Path rutaCompleta = Paths.get("demo/src/main/resources/images");
            Path ruta_eliminar_imagen = Paths.get(rutaCompleta+"/"+image.getName()+"."+ image.getExtension());
            Files.delete(ruta_eliminar_imagen);
        }catch (IOException e) {
            throw new RuntimeException("Error al eliminar la imagen: " + e.getMessage());
        }
        imageservice.deleteById(id);
        return "Imagen eliminada con id: " + id;
    }
}
