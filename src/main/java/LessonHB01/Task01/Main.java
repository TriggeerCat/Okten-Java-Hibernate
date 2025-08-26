package LessonHB01.Task01;

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
                .addAnnotatedClass(Word.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(new Word("Flower"));
        session.persist(new Word("Goat"));
        session.persist(new Word("Skeleton"));
        session.persist(new Word("Fish"));
        session.persist(new Word("Robot"));
        session.persist(new Word("Second goat"));

        List<Word> words = session.createQuery("select w from Word w", Word.class).list();
        System.out.println(words);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
