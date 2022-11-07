package com.apirest.sql.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TypeDocument")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class TypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "type_document", cascade = CascadeType.ALL)
    private List<Person> persons = new ArrayList<>();

}
