package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String DB_USER = "****";
    private static final String DB_PASS = "****";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ex_jdbc";
    private static SessionFactory sessionFactory;

    private Util() {
    }

    public static Connection getConnection() throws SQLException,
            ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        return DriverManager.getConnection(DB_URL, DB_USER,
                DB_PASS);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();

                properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                properties.put(Environment.URL, DB_URL);
                properties.put(Environment.USER, DB_USER);
                properties.put(Environment.PASS, DB_PASS);
                properties.put(Environment.FORMAT_SQL, "true");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                //properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.POOL_SIZE, "10");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                sessionFactory = configuration.buildSessionFactory();

                return sessionFactory;
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e.getMessage());
            }
        }
        return sessionFactory;
    }

}
