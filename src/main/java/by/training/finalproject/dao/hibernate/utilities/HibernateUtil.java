package by.training.finalproject.dao.hibernate.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure(new File("D:\\finalProject\\src\\main\\resources\\db\\hibernate.cfg.xml")).buildSessionFactory();
        } catch (Throwable e) {
            System.out.println("FFFFFFFFFFFF");
            System.err.println(e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void open() {
        buildSessionFactory();
    }

    public static void close() {
        getSessionFactory().close();
    }
}
