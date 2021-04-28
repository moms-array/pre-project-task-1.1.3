package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
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
}
