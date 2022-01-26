package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        User user = new User("Pit","Sam", (byte) 12);
        User user1 = new User("Sam","Pit", (byte) 32);
        User user2 = new User("Bob","Smit", (byte) 22);
        User user3 = new User("Smit","Bob", (byte) 42);
        User user4 = new User("Neo","Triniti", (byte) 2);
        UserService userService = new UserServiceImpl();
        }
    }
