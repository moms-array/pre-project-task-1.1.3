package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao_jdbc;
    public UserServiceImpl(){
        dao_jdbc = new UserDaoJDBCImpl();
    }

    public void createUsersTable() {
        dao_jdbc.createUsersTable();
    }

    public void dropUsersTable() {
        dao_jdbc.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        dao_jdbc.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        dao_jdbc.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dao_jdbc.getAllUsers();
    }

    public void cleanUsersTable() {
        dao_jdbc.cleanUsersTable();
    }
}
