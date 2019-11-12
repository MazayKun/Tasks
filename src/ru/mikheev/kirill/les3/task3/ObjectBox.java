package ru.mikheev.kirill.les3.task3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kirill Mikheev
 * @version 2.0
 *
 * Тот же класс, что и во 2 задани, но изменен для комфортного наследования (изменен метод addObject)
 */

public class ObjectBox {

    /** Коллекция элементов, хранящихся в этом классе  */
    private Set<Object> members = new HashSet<>();

    /**
     * Конструктор по умолчанию
     */
    public ObjectBox(){
        super();
    }

    /**
     * Метод добавляет в коллекцию новый элемент
     * @param obj элемент для добавления
     * @throws ClassCastException добавлен для классов наследников
     */
    public void addObject(Object obj) throws ClassCastException {
        members.add(obj);
    }

    /**
     * Метод проверяет наличие данного метода в коллекции и при наличии удаляет
     * @param obj элемент на удаление
     * @return null если такого объекта в коллекции нет, иначе ссылку на удаленный объект
     */
    public Object deleteObject(Object obj) {
        return members.remove(obj);
    }

    /**
     * Выводит в строку через пробел все элементы коллекции
     */
    public void dump() {
        for (Object tmp: members) {
            System.out.print(tmp.toString() + " ");
        }
        System.out.print("\n");
    }
}
