package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
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
    }
}
