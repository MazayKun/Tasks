package ru.mikheev.kirill.les3.task2;

import java.util.ArrayList;

/**
 * @author Kirill Mikheev
 * @version 1.0
 *
 * Класс, предназначенный для хранения коллекции объектов тип Object
 */

public class ObjectBox {

    /** Коллекция элементов, хранящихся в этом классе  */
    private ArrayList<Object> members = new ArrayList<>();

    /**
     * Конструктор по умолчанию
     */
    public ObjectBox(){
        super();
    }

    /**
     * Метод добавляет в коллекцию новый элемент
     * @param obj элемент для добавления
     */
    public void addObject(Object obj) {
        members.add(obj);
    }

    /**
     * Метод проверяет наличие данного метода в коллекции и при наличии удаляет
     * @param obj элемент на удаление
     * @return null если такого объекта в коллекции нет, иначе ссылку на удаленный объект
     */
    public Object deleteObject(Object obj) {
        if(members.contains(obj)){
            members.remove(obj);
            return obj;
        }else{
            return null;
        }
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
