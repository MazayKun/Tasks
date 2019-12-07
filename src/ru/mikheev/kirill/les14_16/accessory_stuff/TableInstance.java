package ru.mikheev.kirill.les14_16.accessory_stuff;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс, который обобщает все возможные классы записей в таблицы для упрощенной работы с TableDAO
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface TableInstance {

    /**
     * Метод возвращает готовую строку для добавления в таблицу
     * Таким образом TableDAO не нужно париться над каждым запросом в отдельности
     * @return кусок закпроса, в котором содержатсья все данные об объекте сразу в укомплектованном виде
     */
    String getAddRequestFields();

    /**
     * Создаем запись на освнове резалт сета, который был получен из базы
     * @param resultSet данные, полученные из таблицы
     * @return новую запись
     */
    TableInstance getInstanceBasedOnResultSet(ResultSet resultSet) throws SQLException;
}
