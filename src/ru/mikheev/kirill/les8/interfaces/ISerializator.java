package ru.mikheev.kirill.les8.interfaces;

/**
 * Интерфейс объявляет контракт на создание методов для сериализации и десериализации разных объектов
 * @author Kirill Mikheev
 * @version 1.0
 */

public interface ISerializator {
    /**
     * Сериализует переданный объект и сохраняет в файл с заданным именем
     * @param object объект на сериализацию
     * @param file файл, в который сохраняется результат
     */
    void serialize (Object object, String file);

    /**
     * Десериализует объект из переданного файла
     * @param file файл, из которого нужно десериализовать объект
     * @return результат десериализации
     */
    Object deSerialize(String file);
}
