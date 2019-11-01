package ru.mikheev.kirill.les3.task3;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Kirill Mikheev
 * @version 1.0
 *
 * Тот же MathBox, но модифицированный под 3 задание
 * Наследует класс ObjectBox, который лежит в этом же пакете
 */

public class MathBox extends ru.mikheev.kirill.les3.task3.ObjectBox {
    /** Коллекция объектов типа Number, которую хранит класс */
    private ArrayList<Number> members;

    /**
     * Конструктор, принимающий массив Number, и сохраняющий их в коллекцию
     * @param members массиав Number
     */
    public MathBox(Number[] members){
        super();
        this.members = new ArrayList<>(Arrays.asList(members));
    }

    /**
     * Перепределнный метод, котоырй добавляет объект в коллекцию, выдает исключение, если объект не Number
     * @param obj элемент для добавления
     * @throws ClassCastException выдается, если объект не является Number
     */
    @Override
    public void addObject(Object obj) throws ClassCastException{
        if (!(obj instanceof Number)){
            throw new ClassCastException();
        }
        members.add((Number) obj);
    }

    /**
     * Метод суммирует все элементы коллекции
     * @return объект Number - сумма всей коллекции
     */
    public Number summator() {
        Double sumD = 0d;

        for (Number tmp : members){
            sumD += tmp.doubleValue();
        }
        final Double tmpD = sumD;

        return createNumber(tmpD);
    }

    /**
     * Метод делит все числа коллекции на переданное число (На самом деле создается новая коллекция на замену старой)
     * @param split число, на которое нужно разделить коллекцию
     * @return 0 если все прошло успешно, -1 если произошло деление на 0
     */
    public int splitter(Number split) {
        if(split.doubleValue() == 0) {
            return -1;
        }
        ArrayList<Number> newArr= new ArrayList<>();
        for (Number tmp : members) {
            Double answ = tmp.doubleValue() / split.doubleValue();
            newArr.add(createNumber(answ));
        }
        return 0;
    }

    /**
     * Возвращает коллекцию хранимых объектов
     * @return коллекция
     */
    public ArrayList<Number> getMembers() {
        return members;
    }

    /**
     * Метод возвращает строку перечислаения всех элементов через пробел в формате Double
     * @return строка элементов
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Number tmp : members){
            sb.append(tmp.doubleValue());
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Метод получет на вход Integer и если находит в коллекции элемент Double,
     * представление которого соответствует данному, то удаляет его
     * @param toRemove  Integer на удаление
     * @return null если элемент не найдет или объект Number, который был удален
     */
    public Number remove(Integer toRemove) {
        boolean flag = false;
        Number r = null;
        for (Number tmp : members){
            if(tmp.doubleValue() == toRemove.doubleValue()){
                flag = true;
                r = tmp;
            }
        }
        if(flag){
            members.remove(r);
            return r;
        }else{
            return null;
        }
    }

    /**
     * Возвращает хэш-код объекта, равный значению первого элемента в коллекции, если коллекция пустая, то выдает 0
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return members.isEmpty() ? 0 : members.get(0).intValue();
    }

    /**
     * Метод проверяет равенство переданному объекту
     * Метод сначала проверяет не передали ли ему null, если передали, то выдает false
     * Потом проверяет является ли obj MathBox, если нет, то выдает false
     * Далее сравниваются ссылки, если они равны, то выдает true
     * В конце сравниваются длины хранимых коллекций, если они не равны, то выдает false, если же они равны, то
     * проводит поэлементное сравнение, если все эелементы равны, то выдает true, иначе false
     * @param obj объект для сравнения
     * @return true если объекты равны и false если нет
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(! (obj instanceof ru.mikheev.kirill.les3.task1.MathBox)){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(this.members.size() != ((ru.mikheev.kirill.les3.task1.MathBox)obj).getMembers().size()){
            return false;
        }
        boolean flag = true;
        ArrayList<Number> tmp = ((ru.mikheev.kirill.les3.task1.MathBox) obj).getMembers();
        for (int i = 0; i < members.size(); i++){
            if(!members.get(i).equals(tmp.get(i))){
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * Метод проверяет наличие данного метода в коллекции и при наличии удаляет
     * @param obj элемент на удаление
     * @return null если такого объекта в коллекции нет, иначе ссылку на удаленный объект
     */
    @Override
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
    @Override
    public void dump() {
        for (Number tmp: members) {
            System.out.print(tmp.doubleValue() + " ");
        }
        System.out.print("\n");
    }

    /**
     * Вспомогательный метод, который получает на вход Double и упаковывет его в объект Number
     * @param value Double на упаковку
     * @return Анонимный объект Number
     */
    private Number createNumber(Double value) {
        return new Number() {
            Double val = value;
            @Override
            public int intValue() {
                return val.intValue();
            }

            @Override
            public long longValue() {
                return val.longValue();
            }

            @Override
            public float floatValue() {
                return val.floatValue();
            }

            @Override
            public double doubleValue() {
                return val.doubleValue();
            }
        };
    }
}
