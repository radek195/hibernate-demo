package org.example;

import org.example.model.entities.SchoolSubject;
import org.example.model.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateDemo {
    public static void main(String[] args) {

        SchoolSubject schoolSubject = new SchoolSubject();
        schoolSubject.setId(105);
        schoolSubject.setName("Geometry");

        Student student = new Student();
        student.setId(188);
        student.setName("Tom");
        student.setSchoolSubject(schoolSubject);

        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(SchoolSubject.class)
                .addAnnotatedClass(Student.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.persist(schoolSubject);
        session.persist(student);

        Student retrievedStudent = session.get(Student.class, 188);
        System.out.println(retrievedStudent);
        transaction.commit();

    }
}