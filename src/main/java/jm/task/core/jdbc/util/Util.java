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

    public static Connection getConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "ex_jdbc";
        String userName = "****";
        String password = "****";

        return getConnection(hostName, dbName, userName, password);
    }

    public static Connection getConnection(String hostName, String dbName,
                                           String userName, String password) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection connection = DriverManager.getConnection(connectionURL, userName,
                password);
        connection.setAutoCommit(true);

        return connection;
    }


    private static final SessionFactory sessionFactory = buildSessionFactory();

    private Util() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://localhost:3306/ex_jdbc?serverTimezone=UTC&useSSL=false");
            properties.put(Environment.USER, "****");
            properties.put(Environment.PASS, "****");
            properties.put(Environment.FORMAT_SQL, "true");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            //properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.POOL_SIZE, "10");

            return new Configuration().setProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
