package LessonHB02.Task01and02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(new Student("Roman", 20, Arrays.asList(new Course("Java"), new Course("Javascript"))));

        Student artem = new Student("Artem", 20);

        session.persist(new Course("Javascript", artem));
        session.persist(new Course("Knee-breaking", artem));

        session.createQuery("select c.student from Course c where c.name = 'Javascript'", Student.class)
                .list().forEach(System.out::println);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
