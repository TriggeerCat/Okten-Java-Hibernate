package LessonHB02.Task03;

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
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Employee.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee e1 = new Employee("Roman", 1500);
        Employee e2 = new Employee("Artem", 1225);
        Employee e3 = new Employee("Yura", 1103);
        Employee e4 = new Employee("Sanya", 1170);

        session.persist(new Department("Programmer", Arrays.asList(e1, e2)));
        session.persist(new Department("Board Games", Arrays.asList(e3, e4)));

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}

// 3. Створіть клас Employee з полями: id, name, salary, department.
// Використовуйте анотації для створення відповідних таблиць у базі даних і зв’язку з іншими класами
// (наприклад, Department). Напишіть програму для:
//            Збереження даних про співробітників і департаменти в базу даних.
//            Отримання списку співробітників з певного департаменту.
//            Фільтрації співробітників за зарплатою та сортування їх за іменем в порядку спадання за допомогою JPQL або Criteria API.