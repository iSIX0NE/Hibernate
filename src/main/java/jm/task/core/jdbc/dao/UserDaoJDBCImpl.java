package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getJDBCConnection();
    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("""
                    create table if not exists users(
                    id serial,
                    firstname varchar(100) not NULL,
                    lastname varchar(100) not NULL,
                    age int not NULL);
                    """);
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу");
        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("""
                    drop table users
                    """);
        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу");
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    insert into users (firstname,lastname,age)
                    values (?,?,?)
                    """);
            {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Не удалось создать User`ов");
        }

    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                    delete from users
                    where id=?
                    """);
            {
                preparedStatement.setLong(1,id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось удалить User`ов");
        }

    }

    public List<User> getAllUsers() {
       List<User> list = new ArrayList<>();
       try {
           Statement statement = connection.createStatement();
           ResultSet rs = statement.executeQuery("""
                   select * from users
                   """);
           while (rs.next()){
               list.add(new User(
                       rs.getLong(1),
                       rs.getString(2),
                       rs.getString(3),
                       rs.getByte(4)));
           }
       } catch (SQLException e) {
           System.out.println("Не удалось вывести в консоль User`ов");
       }
       return list;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("""
                    TRUNCATE TABLE Users
                    """);
        } catch (SQLException e) {
            System.out.println("Не удалось отчистить таблицу");
        }

    }
}
