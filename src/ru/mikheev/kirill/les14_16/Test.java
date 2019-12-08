package ru.mikheev.kirill.les14_16;

import ru.mikheev.kirill.les14_16.accessory_stuff.RoleDefinitions;
import ru.mikheev.kirill.les14_16.instances.User;
import ru.mikheev.kirill.les14_16.instances.UserRole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Метод для тестирования всех пунктов дз
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Test {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "admin");
            TableDAO tableManager = new TableDAO(connection);
            User user1 = new User("Ivan", "01.01.2001", "_-_KittyDestroyer_-_", "Moscow", "Ivan01012001@omail.com", "description");
            User user2 = new User("Mark", "02.02.2002", "hi", "Moscow", "Mark02022002@omail.com", "description");
            User user3 = new User("Leo", "03.03.2003", "Leogo", "Moscow", "Leo03032003@omail.com", "description");
            tableManager.addInstance(connection, user1);
            tableManager.addInstance(connection, user2);
            tableManager.addInstance(connection, user3);
            tableManager.addRoleByParameters(connection, 0, RoleDefinitions.ADMINISTRATION, "dfghjkl;");
            tableManager.getParamSample(connection, "hi", "Mark");
            UserRole userRole1 = new UserRole(10, 10);
            UserRole userRole2 = new UserRole(20, 20);
            UserRole userRole3 = new UserRole(30, 30);
            tableManager.addUserRolesWithSavePoint(connection, userRole1, userRole2, userRole3);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
