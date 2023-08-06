package Controller.Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static HibernateUtils hibernateUtils;

    private final SessionFactory sessionFactory;

    private HibernateUtils() {
        // Initialize the SessionFactory using the existing file
        Configuration configuration = new Configuration().configure("Database.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static HibernateUtils getInstance() {
        if (hibernateUtils == null) {
            hibernateUtils = new HibernateUtils();
        }

        return hibernateUtils;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
