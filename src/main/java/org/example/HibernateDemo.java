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

        //1. retrieve list of results - look into 4.
        Query<CreditCard> query = session.createQuery("from CreditCard", CreditCard.class);
        List<CreditCard> retrievedCreditCards = query.list();

        //2. retrieve unique result - look into 4.
        Query<CreditCard> query2 = session.createQuery("from CreditCard where id = 12", CreditCard.class);
        CreditCard retrievedCreditCard = query2.uniqueResult();

        //3. retrieve specific values from unique result - look into 4.
        Query<Object[]> query3 = session.createQuery("select cvv, issueDateTime from CreditCard where id = 12", Object[].class);
        Object[] values = query3.uniqueResult();

        //4. retrieve list of results with selectionQuery
        List<CreditCard> creditCards = session.createSelectionQuery("from CreditCard", CreditCard.class).getResultList();

        //5. retrieve list of results with selectionQuery
        List<CreditCard> creditCards2 = session
                .createSelectionQuery("from CreditCard where id = :id", CreditCard.class)
                .setParameter("id", 14)
                .getResultList();

        //6. retrieve list of results with selectionQuery
        CreditCard creditCardUnique = session
                .createSelectionQuery("from CreditCard where id = :id and cvv = ?1", CreditCard.class)
                .setParameter("id", 14)
                .setParameter(1, 481)
                .getSingleResult();


        tx.commit();
        session.close();

        //1. result
        System.out.println("Result 1.");
        retrievedCreditCards.forEach(System.out::println);

        //2. result
        System.out.println("Result 2.");
        System.out.println("Single card retrieved: " + retrievedCreditCard);

        //3. result
        System.out.println("Result 3.");
        for (Object value : values) {
            System.out.println(value);
        }

        //4. result
        System.out.println("4. results");
        for (CreditCard creditCard : creditCards) {
            System.out.println(creditCard);
        }

        //5. result
        System.out.println("Unique result 5.");
        System.out.println(creditCards2.get(0));

        //6. result
        System.out.println("Unique result 6.");
        System.out.println(creditCardUnique);
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