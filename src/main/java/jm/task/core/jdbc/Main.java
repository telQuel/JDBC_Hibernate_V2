package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 16);
        System.out.printf("User с именем – %s добавлен в базу данных\n", "Ivan");
        userService.saveUser("Ivan2", "Ivanov2", (byte) 20);
        System.out.printf("User с именем – %s добавлен в базу данных\n", "Ivan2");
        userService.saveUser("Ivan3", "Ivanov3", (byte) 35);
        System.out.printf("User с именем – %s добавлен в базу данных\n", "Ivan3");
        userService.saveUser("Ivan4", "Ivanov4", (byte) 22);
        System.out.printf("User с именем – %s добавлен в базу данных\n", "Ivan4");

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
