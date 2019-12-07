package ru.mikheev.kirill.les14_16.instances;

import ru.mikheev.kirill.les14_16.accessory_stuff.RoleDefinitions;
import ru.mikheev.kirill.les14_16.accessory_stuff.TableInstance;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс записи в таблице ROLE
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Role implements TableInstance {

    /** Глобальный ID роли  */
    private static int global_role_id = 0;

    /** Поля каждой записи */
    private int id;
    private RoleDefinitions name;
    private String description;

    /**
     * Конструктор принимающий на вход значения всех полей кроме id, который генерируется отдельно
     * TODO : то же самое, что и в замечании для записи User
     */
    public Role(RoleDefinitions name, String description) {
        this.id = global_role_id;
        global_role_id++;
        this.name = name;
        this.description = description;
    }

    /**
     * Приватный конструктор для создания объекта на основе данны из таблицы
     */
    private Role(int id, RoleDefinitions name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает заполненный шаблон добавления этого объекта в таблицу
     * @return готовый запрос на добавление
     */
    @Override
    public String getAddRequestFields() {
        return "INSERT INTO postgres.public.\"ROLE\"(id, name, description) VALUES ("
                + this.id + ", '"
                + this.name.toString() +  "', '"
                + this.description + "');";
    }

    /**
     * Создание нового объекта на основе резалт сета, полученного из таблицы
     * @param resultSet данные, полученные из таблицы
     * @return новую запись
     * @throws SQLException
     */
    @Override
    public Role getInstanceBasedOnResultSet(ResultSet resultSet) throws SQLException {
        Role role = null;
        if (resultSet.next()){
            role = new Role(
                    resultSet.getInt(1),
                    RoleDefinitions.getByName(resultSet.getString(2)),
                    resultSet.getString(3)
            );
        }
        return role;
    }
}
