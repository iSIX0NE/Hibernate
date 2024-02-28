package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Collections;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery("""
                    create table if not exists users(
                    id serial,
                    firstname varchar(100) not NULL,
                    lastname varchar(100) not NULL,
                    age int not NULL);
                    """).executeUpdate();
            session.getTransaction().commit();


        } catch (Exception e) {
            System.out.println("Не удалось создать таблицу");
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery("""
                    DROP TABLE users
                    """).executeUpdate();
            session.getTransaction().commit();


        } catch (Exception e) {
            System.out.println("Не удалось удалить таблицу");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Не удалось создать User`ов");
        }

    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSession()) {
            session.getTransaction().begin();
            session.delete(session.get(User.class,id));
            session.getTransaction().commit();
        }catch (Exception e) {
            System.out.println("Не удалось удалить User`ов");
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("SELECT z FROM User z");
            List<User> users = query.getResultList();
            return users;
        } catch (Exception e) {
            System.out.println("Не удалось создать User`ов");
        }

        return Collections.emptyList();
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE Users").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e) {
            System.out.println("Не удалось отчистить таблицу");
        }

    }


    }
