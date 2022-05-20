package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {

    }
    private static Connection connection;

    static {
        try {
            connection = Util.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS ex_jdbc.user (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age TINYINT NOT NULL);");
    }

    public void dropUsersTable() throws SQLException, ClassNotFoundException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS user");
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("INSERT INTO ex_jdbc.user (`name`, `lastName`, `age`) VALUES (\"%s\", \"%s\", %d);", name, lastName, age);
        statement.executeUpdate(sql);
    }

    public void removeUserById(long id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = String.format("DELETE FROM user WHERE id = %s", id);
        statement.executeUpdate(sql);
    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
        List<User> list = new java.util.ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong(1));
            user.setName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setAge(resultSet.getByte(4));
            list.add(user);
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "TRUNCATE TABLE user";
        statement.executeUpdate(sql);
    }
}

