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
@ToString(exclude = "students") //exclude needed to avoid stack overflow error
@EqualsAndHashCode(exclude="students") //exclude needed to avoid stack overflow error
public class SchoolSubject {

    @Id
    private int id;
    private String name;
    @ManyToMany
    private List<Student> students;
}
