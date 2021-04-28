package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl  implements UserDao {
    User user;
    private Connection connection;

    public UserDaoJDBCImpl() {
    }
    @Override
    public void createUsersTable() {
        Statement statement = null;
        String sql = "CREATE TABLE if not exists User(id BIGINT not null  AUTO_INCREMENT PRIMARY KEY , name varchar(24), lastname varchar(24), age TINYINT)";
        try{
            connection = Util.getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("новая таблица не добавлена");;
        }finally {
            try {
                connection.close();
            }catch (SQLException e){}
        }
    }

    @Override
    public void dropUsersTable() {
        Statement statement = null;
        String sql = "DROP TABLE User";
        try{
            connection = Util.getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("table is DELETE");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("указанную таблицу не удлалось удалить");
        }finally {
            try {
                connection.close();
            }catch (SQLException e){}
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Statement statement;
        User user = new User(name,lastName,age);
        String sql = "INSERT INTO User(NAME , LASTNAME, AGE) VALUES('" + user.getName() + "', '"
                + user.getLastName() + "', " + user.getAge() + ")";
        try{
            connection = Util.getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("user " + name + " is add");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("user " + name + " не был добавлен в таблицу");
        }finally {
            try {
                connection.close();
            }catch (SQLException e){}
        }
    }

    @Override
    public void removeUserById(long id) {
        Statement statement ;
        String sql = "DELETE from User where  (id=" + id + ")";
        try{
            connection = Util.getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("User with id = " + id + " is delete");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("пользователь с id = " + id + " не был удален");
        }finally {
            try {
                connection.close();
            }catch (SQLException e){}
        }
    }

    @Override
    public List<User> getAllUsers() {
        Statement statement = null;
        String sql = "SELECT * FROM User";
        List<User> list = new ArrayList<>();
        try{
            connection = Util.getConnection();
            statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
            System.out.println("list users is get");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("не удалось получить список пользователей");
        }finally {
            try {
                connection.close();
            }catch (SQLException e){}
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Statement statement = null;
        String sql = "DELETE from User";
        try{
            connection = Util.getConnection();
            statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("all users is  delete");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("не удалось удалить всех пользователей");
        }finally {
            try {
                connection.close();
            }catch (SQLException e){}
        }
    }
}
