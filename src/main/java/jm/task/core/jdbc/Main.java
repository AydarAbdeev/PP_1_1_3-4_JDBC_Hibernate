package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Alex", "Little", (byte) 41);
        userDao.saveUser("Steve", "Brown", (byte) 33);
        userDao.saveUser("Joshua", "Jackson", (byte) 35);
        userDao.saveUser("Phil", "Nord", (byte) 27);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

        UserDao userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Tony", "Kroos", (byte) 34);
        userDaoHibernate.saveUser("Luka", "Modric", (byte) 38);
        userDaoHibernate.saveUser("Jude", "Bellingham", (byte) 20);
        userDaoHibernate.saveUser("Dani", "Carvajal", (byte) 32);
        System.out.println(userDaoHibernate.getAllUsers());
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
