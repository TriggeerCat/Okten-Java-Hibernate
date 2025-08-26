package LessonHB01.Task02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Car.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(new Car("Lada", Type.FAMILY_CAR, 10, 5000, 2015));
        session.persist(new Car("Lamborghini", Type.SPORTSCAR, 50, 10000000, 2024));
        session.persist(new Car("Volkswagen", Type.DAS_AUTO, 15, 30000, 2021));

        List<Car> carList = session.createQuery("select c from Car c", Car.class).list();
        System.out.println(carList);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
