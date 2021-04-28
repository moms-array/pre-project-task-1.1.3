package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.mapping.MetadataSource;
import org.hibernate.metamodel.Metadata;
import org.hibernate.metamodel.MetadataSources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost:3306/first_lesson";
    private static final String name = "root";
    private static final String password = "e4P-rkE-ZfU-VWn";
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry registry;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, name, password);
        System.out.println("connection successful" + "\n");
        return connection;
    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                Map<String, String> settings = new HashMap<>();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/first_lesson");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "e4P-rkE-ZfU-VWn");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(User.class);
                Metadata metadata = sources.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }catch (Exception e){
                e.printStackTrace();
                if (registry != null){
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
            System.out.println("connection successful");
        }
        return sessionFactory;
    }
    public static void closed(){
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
