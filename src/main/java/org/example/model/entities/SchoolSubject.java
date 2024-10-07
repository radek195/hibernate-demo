package org.example.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class SchoolSubject {

    @Id
    private int id;
    private String name;
    @ManyToOne
    private Student student;
}
