package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.createSQLQuery("CREATE TABLE if not exists User(id BIGINT not null  AUTO_INCREMENT PRIMARY KEY , name varchar(24), lastname varchar(24), age TINYINT)")
                .addEntity(User.class)
                .executeUpdate();
        session.flush();
        session.close();
        System.out.println("Таблица создана");
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.createSQLQuery("DROP TABLE User").addEntity(User.class).executeUpdate();
        session.flush();
        session.close();
        System.out.println("таблца удалена");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        User user = new User(name, lastName, age);
        user.setId(null);
        session.save(user);
        session.flush();
        session.close();
        System.out.println("User:" + user + " was saved");
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        User user = (User) session.get(User.class,id);
        session.delete(user);
        session.flush();
        session.close();
        System.out.println("User with id: " + id + " is delete");
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        return session.createCriteria(User.class).list();
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.clear();
        session.flush();
        session.close();
    }
}
