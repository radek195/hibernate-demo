package org.example.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    private int id;
    private String name;
    @OneToOne
    private SchoolSubject schoolSubject;

}
