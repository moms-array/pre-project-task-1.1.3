package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Main;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl  implements UserDao {

    public UserDaoJDBCImpl() {
    }
    @Override
    public void createUsersTable() {
        try(Connection connection = Util.getConnection()){
            Statement statement=connection.createStatement();
            statement.executeUpdate("CREATE TABLE if not exists User(id BIGINT not null  AUTO_INCREMENT PRIMARY KEY , name varchar(24), lastname varchar(24), age TINYINT)");
            System.out.println("Таблица создана");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("новая таблица не добавлена");;
        }
    }

    @Override
    public void dropUsersTable() {
        try(Connection connection = Util.getConnection()){
            Statement statement=connection.createStatement();
            statement.executeUpdate("DROP TABLE User");
            System.out.println("table is DELETE");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("указанную таблицу не удлалось удалить");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User(NAME , LASTNAME, AGE) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println("user " + name + " is add");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("user " + name + " не был добавлен в таблицу");
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from User where  (id = ?)");
            preparedStatement.setLong(1, id);
            System.out.println("User with id = " + id + " is delete");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("пользователь с id = " + id + " не был удален");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try(Connection connection = Util.getConnection()){
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
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
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try(Connection connection = Util.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE from User");
            System.out.println("all users is  delete");
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("не удалось удалить всех пользователей");
        }
    }
}
