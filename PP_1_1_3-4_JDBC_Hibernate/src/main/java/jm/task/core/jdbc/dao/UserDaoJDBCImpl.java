package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = getConnection().createStatement();) {
            statement.executeUpdate("create table if not exists tableTest (id bigint primary key auto_increment, " +
                    " name varchar (50) not null, " +
                    " lastname varchar (50) not null, " +
                    " age tinyint not null)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = getConnection().createStatement();) {
            statement.executeUpdate("DROP TABLE IF EXISTS tabletest");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement("INSERT INTO tableTest (name,lastName,age) values (?,?,?)");) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement("delete  from tableTest where id = ?");) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();) {
            ResultSet resultSet = statement.executeQuery("select * from tableTest");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = getConnection().createStatement();) {
            statement.executeUpdate("delete from tabletest");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
