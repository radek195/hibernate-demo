package org.example.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SchoolSubject {

    @Id
    private int id;
    private String name;
}
