package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("ASD","asdd",(byte)23);
        userService.saveUser("gfh","fghj",(byte)24);
        userService.saveUser("ghj","ghjk",(byte)25);
        userService.saveUser("hjk","hjkl",(byte)26);
        List<User> lit = userService.getAllUsers();
        for (User x : lit){
            System.out.println(x.toString());
        }
        userService.removeUserById(1);
        userService.cleanUsersTable();



    }
}
