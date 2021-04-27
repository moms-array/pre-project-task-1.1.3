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

    private static final String url = "jdbc:mysql://localhost:3306/first_lesson";
    private static final String name = "root";
    private static final String password = "e4P-rkE-ZfU-VWn";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, name, password);
            System.out.println("connection successful" + "\n");
        return connection;
    }

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