package com.apirest.sql.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Document(collection = "images")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Data
public class ImageMongo {
    @Id
    private String _id;
    private String name;
    private String extension;
    private String url;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    private Person _person;

}
