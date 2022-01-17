package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    public UserDaoJDBCImpl() {
        this.connection = Util.getConnection();
    }

    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(80), lastname VARCHAR(80), age TINYINT)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(create)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Не удалось создать таблицу");
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(drop)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Не удалось удалить таблицу");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT INTO users(name, lastname, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(save)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем - %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.err.printf("Не удалось добавить User  с  именем - %s\n", name);
        }
    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось удалить User");
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String allUsers = "SELECT * FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(allUsers)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Не удалось вывести таблицу на экран");
        }
        return users;
    }

    public void cleanUsersTable() {
        String clean = "TRUNCATE users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(clean)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось очистить таблицу");
        }
    }
}
