package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao user_dao;
    public UserServiceImpl(){
        user_dao = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {
        user_dao.createUsersTable();
    }

    public void dropUsersTable() {
        user_dao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        user_dao.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        user_dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return user_dao.getAllUsers();
    }

    public void cleanUsersTable() {
        user_dao.cleanUsersTable();
    }

}
