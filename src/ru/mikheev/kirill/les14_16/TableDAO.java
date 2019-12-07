package ru.mikheev.kirill.les14_16;

import ru.mikheev.kirill.les14_16.accessory_stuff.RoleDefinitions;
import ru.mikheev.kirill.les14_16.accessory_stuff.TableInstance;
import ru.mikheev.kirill.les14_16.instances.UserRole;

import java.sql.*;

/**
 * Класс взаимодействия с таблицами моей базы данных
 * @author Kirill Mikheev
 * @version 1.0
 */

public class TableDAO {

    /**
     * Конструктор создает все нужные таблицы для работы с заданием
     */
    public TableDAO(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.addBatch(
                "CREATE TABLE postgres.public.\"USER\"( \n" +
                "    id INTEGER, \n" +
                "    name VARCHAR(50) NOT NULL, \n" +
                "    birthday VARCHAR(50) NOT NULL, \n" +
                "    login_id VARCHAR(100) NOT NULL,\n" +
                "    city VARCHAR(100) NOT NULL,\n" +
                "    email VARCHAR(100) NOT NULL,\n" +
                "    description VARCHAR(100) NOT NULL\n" +
                ");"
        );
        statement.addBatch(
                "CREATE TABLE postgres.public.\"ROLE\"( \n" +
                        "    id INTEGER, \n" +
                        "    name VARCHAR(50) NOT NULL, \n" +
                        "    description VARCHAR(100) NOT NULL\n" +
                        ");"
        );
        statement.addBatch(
                "CREATE TABLE postgres.public.\"USER_ROLE\"( \n" +
                        "    id INTEGER, \n" +
                        "    user_id INTEGER, \n" +
                        "    role_id INTEGER \n" +
                        ");"
        );
        statement.executeBatch();
        statement.close();
        connection.commit();
        connection.setAutoCommit(true);

    }

    /**
     * Метод для добавления новой записи в таблицу
     * @param connection канал подключения к базе
     * @param instance запись, которую нужно вставить
     */
    public void addInstance(Connection connection, TableInstance instance) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(instance.getAddRequestFields());
        statement.close();
    }

    /**
     * Параметрезированное добавление в таблицу ROLE (задание 2, пункт а)
     * @param connection соединение
     * @param id айдишник роли
     * @param name элемент RoleDefinitios, соответствующий данной записи
     * @param description описание
     * @throws SQLException
     */
    public void addRoleByParameters(Connection connection, int id, RoleDefinitions name, String description) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into postgres.public.\"ROLE\" (id, name, description) VALUES (?, ?, ?);"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name.toString());
        preparedStatement.setString(3, description);
        preparedStatement.execute();
        preparedStatement.close();
    }

    /**
     * Метод выводит в консоль выборку по паарметру login_id и name одновременно
     * @param connection соединение
     * @param login_id параметр логин для выборки
     * @param name параметр имени для выборки
     */
    public void getParamSample(Connection connection, String login_id, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from postgres.public.\"USER\" where login_id = ? and name = ? ;"
        );
        preparedStatement.setString(1, login_id);
        preparedStatement.setString(2, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            System.out.println("id : " + resultSet.getInt(1) +
                            " | name : " + resultSet.getString(2) +
                            " | birthday : " + resultSet.getString(3) +
                            " | login_id : " + resultSet.getString(4) +
                            " | city : " + resultSet.getString(5) +
                            " | email : " + resultSet.getString(6) +
                            " | description : " + resultSet.getString(7)
            );
        }
    }

    /**
     * Выполняет обавление 3х элементов в таблицу UserRole, но по дороге создает SavePoint и потом откатывается к ней в конце
     * @param connection соединение
     * @param userRole1 1 роль на добавление
     * @param userRole2 2 роль на добавление
     * @param userRole3 3 роль на добавление
     */
    public void addUserRolesWithSavePoint(Connection connection, UserRole userRole1, UserRole userRole2, UserRole userRole3) throws SQLException {
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.execute(userRole1.getAddRequestFields());
        statement.execute(userRole2.getAddRequestFields());
        Savepoint savepoint = connection.setSavepoint();
        statement.execute(userRole3.getAddRequestFields());
        connection.rollback(savepoint);
        connection.commit();
        statement.close();
        connection.setAutoCommit(true);
    }
}
