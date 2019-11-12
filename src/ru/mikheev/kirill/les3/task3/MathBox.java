package ru.mikheev.kirill.les3.task3;

import java.util.*;

/**
 * @author Kirill Mikheev
 * @version 2.0
 *
 * Тот же MathBox, но модифицированный под 3 задание
 * Наследует класс ObjectBox, который лежит в этом же пакете
 */

public class MathBox<T extends Number> extends ru.mikheev.kirill.les3.task3.ObjectBox {

    /** Коллекция объектов типа T, которую хранит класс */
    private Set<T> members;

    /** Случайный айди данного MathBox*/
    private final UUID uuid;

    /**
     * Конструктор, принимающий массив Number, и сохраняющий их в коллекцию
     * @param members массиав Number
     */
    public MathBox(T[] members){
        super();
        this.members = new HashSet<>(Arrays.asList(members));
        uuid = UUID.randomUUID();
    }

    /**
     * Метод суммирует все элементы коллекции  (Как я уже писал выше, тут используется понижение до типа Double)
     * @return объект типа Double приведенный к T - сумма всей коллекции
     */
    @SuppressWarnings("unchecked")
    public T summator() {
        Double sum = 0d;
        for (Number tmp : members){
            sum += tmp.doubleValue();
        }
        return (T)sum;
    }

    /**
     * Метод делит все числа коллекции на переданное число
     * С этим методом так же не обошлось без проблем
     * Во-первых, я по прежнему использую тип Double для всех операций
     * Во-вторых, опять же из-за стирания, мне приходится новые значения сохранять с потерей типав, в итоге я использую
     * метод, который создает анонимный Number, и после деления вся коллекция заменятеся на новую, которая состоит
     * только из анонимных Number
     * @param split число, на которое нужно разделить коллекцию
     * @return 0 если все прошло успешно, -1 если произошло деление на 0
     */
    @SuppressWarnings("unchecked")
    public int splitter(Number split) {
        if(split.doubleValue() == 0) {
            return -1;
        }
        Set<T> newArr= new HashSet<>();
        for (T tmp : members) {
            Double answ = tmp.doubleValue() / split.doubleValue();
            newArr.add((T)createNumber(answ));
        }
        members = newArr;
        return 0;
    }

    /**
     * Возвращает копию коллекции хранимой в данном объекте (чтобы ее нельзя было менять)
     * @return копия коллекции
     */
    @SuppressWarnings("unchecked")
    public Set<T> getMembers() {
        return (HashSet)((HashSet)members).clone();
    }

    /**
     * Метод получет на вход Integer и если находит в коллекции элемент, Double
     * представление которого соответствует Double представлению параметра, то удаляет его
     * @param toRemove  Integer на удаление
     * @return null если элемент не найдет или объект Number, который был удален
     */
    @SuppressWarnings("unchecked")
    public T remove(Integer toRemove) {
        Iterator<T> it = members.iterator();
        boolean isFound = false;
        while (it.hasNext()){
            if(it.next().doubleValue() == toRemove.doubleValue()){
                it.remove();
                isFound = true;
                break;
            }
        }
        return isFound ? (T)toRemove : null;
    }

    /**
     * Возвращает хэш-код объекта, равный интовому значению первого элемента в коллекции,
     * если коллекция пустая, то выдает 0
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    /**
     * Метод проверяет равенство переданному объекту
     * 1)Сначала он проверяет не передали ли ему null, если передали, то выдает false
     * 2)Потом проверяет является ли obj MathBox, если нет, то выдает false
     * 3)Далее сравниваются ссылки, если они равны, то выдает true
     * 4)В конце сравниваются длины хранимых коллекций, если они не равны, то выдает false, если же они равны, то
     * проводит поэлементное сравнение, если все эелементы равны, то выдает true, иначе false
     * @param obj объект для сравнения
     * @return true если объекты равны и false если нет
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(! (obj instanceof MathBox)){
            return false;
        }
        if(this == obj){
            return true;
        }
        if(this.members.size() != ((MathBox)obj).getMembers().size()){
            return false;
        }
        Set<Number> tmp = ((MathBox) obj).getMembers();
        return members.equals(tmp);
    }

    /**
     * Метод возвращает строку перечислаения всех элементов через пробел в формате Double
     * @return строка элементов
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T tmp : members){
            sb.append(tmp.doubleValue());
            sb.append(" ");
        }
        return sb.toString();
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
        members.add((T) obj);
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
        System.out.println(this.toString());
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
