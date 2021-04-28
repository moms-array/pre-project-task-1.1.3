package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Michal", "Conor", (byte)45);
        userService.saveUser("Anna", "Dolgorukova", (byte)98);
        userService.saveUser("Petr", "Mihailov", (byte)32);
        userService.saveUser("Mary", "Patrushina", (byte) 28);
        List<User> list = userService.getAllUsers();
        for(User x : list){
            System.out.println(x.toString());
        }
        list.clear();
        userService.removeUserById(2);
        list = userService.getAllUsers();
        for(User x : list){
            System.out.println(x.toString());
        }
        userService.cleanUsersTable();
        list.clear();
        list = userService.getAllUsers();
        for(User x : list){
            System.out.println(x.toString());
        }
        userService.dropUsersTable();


    }
}
