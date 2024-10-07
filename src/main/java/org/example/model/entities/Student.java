package org.example.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Student {

    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "student")
    //student comes from SchoolSubject class
    private List<SchoolSubject> schoolSubject;

}
