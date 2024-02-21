package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session session;
    private Transaction transaction;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(80), lastname VARCHAR(80), age TINYINT)";
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(create).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery(drop).addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = session.load(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return Util.getSessionFactory().openSession().createQuery("FROM User", User.class).list();
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE from User").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}