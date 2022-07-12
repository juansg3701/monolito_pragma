package com.apirest.sql.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "images")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
@Setter @Getter @ToString
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "extension")
    private String extension;

    @Column(name = "url")
    private String url;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private Date updatedAt;

    //@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", insertable = true, updatable = true, nullable = false)
    private Person person = new Person();

    public int getPerson() {
        return person.getId();
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
