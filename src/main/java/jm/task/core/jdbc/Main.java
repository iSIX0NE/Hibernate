package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Александр","Пушин", (byte) 74);
        userService.saveUser("Иван","Иванов", (byte) 64);
        userService.saveUser("Алексей","Драгунов", (byte) 13);
        userService.saveUser("Олег","Гарпун", (byte) 26);
        userService.getAllUsers().forEach(System.out::println);
        userService.createUsersTable();
        userService.dropUsersTable();

    }
}
