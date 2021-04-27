package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static final String url = "jdbc:mysql://localhost:3306/first_lesson";
    private static final String name = "root";
    private static final String password = "e4P-rkE-ZfU-VWn";
    private Connection connection = null;


    public UserServiceImpl(){
        try {
            System.out.println("try connect");
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,name,password);
            System.out.println("connection successful");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("fail");
            throwables.printStackTrace();
        }
    }

    public void createUsersTable() {
        Statement statement = null;
        String sql = "CREATE TABLE if not exists users(id INTEGER not null  AUTO_INCREMENT PRIMARY KEY , name varchar(24), lastname varchar(24), age TINYINT);";
        try{
            statement=connection.createStatement();
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        String sql = "DROP TABLE users";
        try{
            statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("table is DELETE");
        }catch (SQLException e){
            System.out.println("такой таблицы не существует");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        Statement statement = null;
        String sql = "INSERT INTO users(NAME , LASTNAME, AGE) VALUES('" + name + "', '"
                + lastName + "', " + age + ")";
        try{
            statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("user " + name + " is add");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Statement statement = null;
        String sql = "DELETE from users where  (id=" + id + ")";
        try{
            statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("user is delete");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        Statement statement = null;
        String sql = "SELECT * FROM users";
        List<User> list = new ArrayList<>();
        try{
            statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("list users is get");

            while (resultSet.next()){
                list.add(new User(resultSet.getString(2),resultSet.getString(3),resultSet.getByte(4)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        String sql = "DELETE from users";
        try{
            statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("all users is  delete");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
