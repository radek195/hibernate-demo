package org.example.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Student {

    @Id
    private int id;
    private String name;
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY) //FetchType.EAGER
    //student comes from SchoolSubject class
    private List<SchoolSubject> schoolSubject;

}
