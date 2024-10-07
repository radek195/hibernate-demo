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

        Student studentTom = new Student();
        studentTom.setId(188);
        studentTom.setName("Tom");
        studentTom.setSchoolSubjects(List.of(geometry, math));

        Student studentJean = new Student();
        studentJean.setId(288);
        studentJean.setName("Jean");
        studentJean.setSchoolSubjects(List.of(geometry, math));

        Student studentMark = new Student();
        studentMark.setId(148);
        studentMark.setName("Mark");
        studentMark.setSchoolSubjects(List.of(math));

        geometry.setStudents(List.of(studentTom, studentJean));
        math.setStudents(List.of(studentTom, studentMark, studentJean));

        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(SchoolSubject.class)
                .addAnnotatedClass(Student.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.persist(studentTom);
        session.persist(studentJean);
        session.persist(studentMark);
        session.persist(geometry);
        session.persist(math);

        Student retrievedTom = session.get(Student.class, 188);
        Student retrievedJean = session.get(Student.class, 288);
        Student retrievedMark = session.get(Student.class, 148);

        SchoolSubject retrievedMath = session.get(SchoolSubject.class, 48);
        SchoolSubject retrievedGeometry = session.get(SchoolSubject.class, 105);
        transaction.commit();

        System.out.println("=== RESULTS ===");

        System.out.println(retrievedTom);
        System.out.println("Toms school subjects: " + retrievedTom.getSchoolSubjects());

        System.out.println(retrievedJean);
        System.out.println("Jeans school subjects: " + retrievedJean.getSchoolSubjects());

        System.out.println(retrievedMark);
        System.out.println("Marks school subjects: " + retrievedMark.getSchoolSubjects());

        System.out.println(retrievedMath);
        System.out.println("Math students: " + retrievedMath.getStudents());
        System.out.println(retrievedGeometry);
        System.out.println("Geometry students: " + retrievedGeometry.getStudents());

        System.out.println("=== END OF RESULTS ===");

    }
}