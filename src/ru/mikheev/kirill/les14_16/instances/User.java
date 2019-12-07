package ru.mikheev.kirill.les14_16.instances;

import ru.mikheev.kirill.les14_16.accessory_stuff.TableInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс записи в таблице USER
 * @author Kirill Mikheev
 * @version 1.0
 */

public class User implements TableInstance {
    /** Глобальный ID пользователей  */
    private static int global_id = 0;

    /** Поля каждой записи */
    private int id;
    private String name;
    private String birthday;
    private String login_ID;
    private String city;
    private String email;
    private String description;

    /**
     * Конструктор принимающий на вход значения всех полей кроме id, который генерируется отдельно
     * TODO : Можно было бы сделать так, чтобы из таблицы сразу принималось значение id последнего пользователя
     * TODO : оно бы сразу сохранялоь в global_id, но у нас тестовое прложение, поэтому я с этим не парился
     */
    public User(String name, String birthday, String login_ID, String city, String email, String description) {
        this.id = global_id;
        global_id++;
        this.name = name;
        this.birthday = birthday;
        this.login_ID = login_ID;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    /**
     * Првиатный конструктор, который нужен для создания нового объекта на основе резалт сета
     */
    private User(int id, String name, String birthday, String login_ID, String city, String email, String description) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.login_ID = login_ID;
        this.city = city;
        this.email = email;
        this.description = description;
    }

    /**
     * Возвращает заполненный шаблон добавления этого объекта в таблицу
     * @return готовый запрос на добавление
     */
    @Override
    public String getAddRequestFields() {
        return "INSERT INTO postgres.public.\"USER\"(id, name, birthday, login_id, city, email, description) VALUES ("
                + this.id + ", '"
                + this.name +  "', '"
                + this.birthday + "', '"
                + this.login_ID +  "', '"
                + this.city +  "', '"
                + this.email +  "', '"
                + this.description + "');";
    }

    /**
     * Создание пользователя на основе данных из таблицы
     * @param resultSet данные, полученные из таблицы
     * @return новую запись
     * @throws SQLException
     */
    @Override
    public User getInstanceBasedOnResultSet(ResultSet resultSet) throws SQLException {
        User newUser = null;
        if(resultSet.next()){
            newUser = new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return newUser;
    }
}
