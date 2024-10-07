package org.example.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "schoolSubjects") //exclude needed to avoid stack overflow error
@EqualsAndHashCode(exclude = "schoolSubjects") //exclude needed to avoid stack overflow error
public class Student {

    @Id
    private int id;
    private String name;
    @ManyToMany(mappedBy = "students")
    //student comes from SchoolSubject class
    private List<SchoolSubject> schoolSubjects;

}
