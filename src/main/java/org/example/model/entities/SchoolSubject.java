package org.example.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SchoolSubject {

    @Id
    private int id;
    private String name;
    @ManyToOne
    private Student student;
}
