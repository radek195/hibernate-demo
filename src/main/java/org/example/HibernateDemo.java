package org.example;

import org.example.model.entities.SchoolSubject;
import org.example.model.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateDemo {
    public static void main(String[] args) {

        SchoolSubject geometry = new SchoolSubject();
        geometry.setId(105);
        geometry.setName("Geometry");

        SchoolSubject math = new SchoolSubject();
        math.setId(48);
        math.setName("Math");

        Student student = new Student();
        student.setId(188);
        student.setName("Tom");
        student.setSchoolSubject(List.of(geometry, math));

        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(SchoolSubject.class)
                .addAnnotatedClass(Student.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.persist(geometry);
        session.persist(math);
        session.persist(student);

        Student retrievedStudent = session.get(Student.class, 188);
        transaction.commit();

        System.out.println(retrievedStudent);

    }
}