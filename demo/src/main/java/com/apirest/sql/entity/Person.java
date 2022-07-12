package com.apirest.sql.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "person")
@AllArgsConstructor @NoArgsConstructor @Setter @Getter @ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "age")
    private int age;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date created_at;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updated_at;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();





}
