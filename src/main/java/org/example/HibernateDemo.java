package org.example;

import org.example.model.CreditCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;
import java.util.Random;

public class HibernateDemo {

    static SessionFactory sessionFactory;

    public static void main(String[] args) {
        setUpFactory();
//        setUpData(); //uncomment this and set hbm2ddl.auto to 'create' to generate random data

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        CreditCard creditCard = new CreditCard(); //NEW state
        creditCard.setId(99); //once values are assigned it is in TRANSIENT state
        creditCard.setCvv(898);
        creditCard.setIssueDateTime(LocalDateTime.now());
        creditCard.setOwnerFullName("Mr John Kowalski");

        session.persist(creditCard); //creditCard goes to PERSISTENT state
        creditCard.setCvv(444); //creditCard got update in PERSISTENT state so the value 444 is saved to database after commit

        CreditCard creditCard2 = new CreditCard(); //NEW state
        creditCard2.setId(2222); //once values are assigned it is in TRANSIENT state
        creditCard2.setCvv(111);
        creditCard2.setIssueDateTime(LocalDateTime.now());
        creditCard2.setOwnerFullName("Mrs Ewa Swoboda");

        session.persist(creditCard2); //creditCard2 goes to PERSISTENT state
        creditCard.setCvv(676); //creditCard2 got update in PERSISTENT state so the value 444 is saved to database after commit (or?)
//        session.remove(creditCard2); //this removes object from lifecycle


        tx.commit();
        session.evict(creditCard2); //creditCard2 goes to detached state
        creditCard2.setCvv(900); //creditCard2 will not be updated in the database to value 90 because it was detached before making a change

    }

    private static void setUpFactory() {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(CreditCard.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    private static void setUpData() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Random r = new Random();

        for (int i = 0; i < 46; i++) {
            CreditCard creditCard = new CreditCard(
                    i,
                    r.nextDouble() > 0.5 ? "Mr Jan Kowalski" : "Mrs Ewa Swoboda",
                    r.nextInt(100, 1000),
                    LocalDateTime.now()
                            .minusMonths(r.nextInt(-3, 3))
                            .plusDays(r.nextInt(2, 15))
                            .minusMinutes(r.nextInt(1, 45)));
            session.persist(creditCard);
        }
        transaction.commit();
        session.close();
    }
}