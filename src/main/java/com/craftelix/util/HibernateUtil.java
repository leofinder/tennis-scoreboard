package com.craftelix.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
@UtilityClass
public class HibernateUtil {

    @Getter
    private static SessionFactory sessionFactory;

    static {
        buildSessionFactory();
    }

    private static void buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                sessionFactory = configuration.buildSessionFactory();
            } catch (Throwable t) {
                log.error("FATAL ERROR. {}", t.getMessage());
                throw t;
            }
        }
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
