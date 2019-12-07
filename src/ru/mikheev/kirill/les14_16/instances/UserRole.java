package ru.mikheev.kirill.les14_16.instances;

import ru.mikheev.kirill.les14_16.accessory_stuff.RoleDefinitions;
import ru.mikheev.kirill.les14_16.accessory_stuff.TableInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс записи в таблице USER_ROLE
 * @author Kirill Mikheev
 * @version 1.0
 */

public class UserRole implements TableInstance {

    /** Глобальный ID роли пользователя */
    private static int global_user_role_id = 0;

    /** Поля каждой записи */
    private int id;
    private int user_id;
    private int role_id;

    /**
     * Конструктор принимающий на вход значения всех полей кроме id, который генерируется отдельно
     * TODO : то же самое, что и в замечании для записи User
     */
    public UserRole(int user_id, int role_id) {
        this.id = global_user_role_id;
        global_user_role_id++;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    /**
     * Приватный конструктор для создания объекта на основе данны из таблицы
     */
    private UserRole(int id, int user_id, int role_id) {
        this.id = id;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    /**
     * Возвращает заполненный шаблон добавления этого объекта в таблицу
     * @return готовый запрос на добавление
     */
    @Override
    public String getAddRequestFields() {
        return "INSERT INTO postgres.public.\"USER_ROLE\"(id, user_id, role_id) VALUES ("
                + this.id + ", "
                + this.user_id + ", "
                + this.role_id + ");";
    }

    /**
     * Создание нового объекта на основе резалт сета, полученного из таблицы
     * @param resultSet данные, полученные из таблицы
     * @return новую запись
     * @throws SQLException
     */
    @Override
    public UserRole getInstanceBasedOnResultSet(ResultSet resultSet) throws SQLException {
        UserRole userRole = null;
        if (resultSet.next()){
            userRole= new UserRole(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3)

            );
        }
        return userRole;
    }
}
