package org.example;

import org.example.model.CreditCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class HibernateDemo {

    static SessionFactory sessionFactory;

    public static void main(String[] args) {
        setUpFactory();
//        setUpData(); //uncomment this and set hbm2ddl.auto to 'create' to generate random data

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        //retrieve list of results
        Query<CreditCard> query = session.createQuery("from CreditCard", CreditCard.class);
        List<CreditCard> retrievedCreditCards = query.list();

        //retrieve unique result
        Query<CreditCard> query2 = session.createQuery("from CreditCard where id = 12", CreditCard.class);
        CreditCard retrievedCreditCard = query2.uniqueResult();

        tx.commit();
        session.close();

        retrievedCreditCards.forEach(System.out::println);
        System.out.println("Single card retrieved: " + retrievedCreditCard);
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