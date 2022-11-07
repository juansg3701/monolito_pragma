package com.apirest.sql.controller;

import com.apirest.sql.dao.person.PersonDAO;
import com.apirest.sql.entity.ImageMongo;
import com.apirest.sql.entity.Person;
import com.apirest.sql.entity.TypeDocument;
import com.apirest.sql.service.imagemongo.ImageMongoService;
import com.apirest.sql.service.person.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ImageMongoRestControllerTest {

    @Mock
    private ImageMongoService imageMongoService;
    @Mock
    private PersonDAO personDAO;
    @InjectMocks
    private ImageMongoRestController mongoRestController;

    private ImageMongo image;
    private Person person;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        person = new Person();
        person.setName("Juan");
        person.setCedula("112323");
        person.setEmail("juan@gmail.com");
        person.setAge(23);
        person.setId(123);

        image = new ImageMongo();
        image.setName("test");
        image.set_id("123123");
        image.setExtension("jpg");
        image.setUrl("http://localhost:8080");
        image.setUpdatedAt(new Date());
        image.setCreatedAt(new Date());
        image.set_person(person);
    }

    @Test
    void getImagesgetImagesMongo() {
        when(imageMongoService.findAll()).thenReturn(Arrays.asList(image));
        assertNotNull(mongoRestController.getImagesgetImagesMongo("123"));
    }

    @Test
    void getImage() {
        assertNotNull(mongoRestController.getImage(image.get_id()));
        assertNotNull(imageMongoService.findById(image.get_id()));
    }

    @Test
    @Disabled
    void addImage() {

        when(imageMongoService.save(image)).thenReturn(image);
        when(personDAO.findById(person.getId())).thenReturn(person);

        MockMultipartFile file = new MockMultipartFile( "file",
                "hello.jpeg",
                String.valueOf(MediaType.IMAGE_JPEG),
                "Hello, World!".getBytes());

        assertNotNull(mongoRestController.addImage(123, file));
    }

    @Test
    void updateImage() {
        when(imageMongoService.update(image)).thenReturn(image);
        System.out.println(mongoRestController.updateImage("123123", Mockito.any()));
        assertNotNull(mongoRestController.updateImage("123123", Mockito.any()));
    }

    @Test
    void deleteImage() {
        assertNotNull(mongoRestController.deleteImage(image.get_id()));
        // verify(imageMongoDAO,times(1)).deleteById(image.get_id());
        doNothing().doThrow().when(imageMongoService).delete(image.get_id());
    }
}