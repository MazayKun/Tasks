package ru.mikheev.kirill.les14_16;

import ru.mikheev.kirill.les14_16.accessory_stuff.RoleDefinitions;
import ru.mikheev.kirill.les14_16.accessory_stuff.TableInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
}
