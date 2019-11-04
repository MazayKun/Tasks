package ru.mikheev.kirill.les3.task1;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс хранит коллекцию объектов типа T и предоставляет функционал для работы с ней.
 *
 * Класс Long хранит до 19 степеней 10 включительно, в свою очередь Double хранит до 16 значимых десятичных
 * цифр включительно, поэтому я решил использовать Double во всех вычислениях. С ним потеря данных
 * минимальна.
 * Подобные ухищрения связаны с затиранием типов дженериков. Я не могу точно определить тип элемента
 * коллекции, а так как там могут быть все типы реализующие Number, в тч и собственные типы, то приходится
 * довольствоваться встроенным функционалом, вроде getLong(), getDouble и тд.
 * В методах, где нужно вернуть T, я так же буду возвращать объект Double.
 * Подобный компромисс обеспечивает максимально возможную работоспособность.
 * (Хотя можно было так же передавать в конструктор объект типа Class, чтобы я точно мог знать, с каким типом я работаю,
 * но в таком случае мы потеряем возможность хранить все типы в коллекции разом)
 *
 * @author Kirill Mikheev
 * @version 2.0
 * @param <T> тип даных реализующий интерфейс Number
 *
 */
public class MathBox <T extends Number> {

    /** Коллекция объектов типа T, которую хранит класс */
    private ArrayList<T> members;

    /**
     * Конструктор, принимающий массив T, и сохраняющий их в коллекцию
     * @param members массиав элементов T
     */
    public MathBox(T[] members){
        this.members = new ArrayList<>(Arrays.asList(members));
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
        ArrayList<T> newArr= new ArrayList<>();
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
    public ArrayList<T> getMembers() {
        return (ArrayList<T>)members.clone();
    }

    /**
     * Метод получет на вход Integer и если находит в коллекции элемент, Double
     * представление которого соответствует Double представлению параметра, то удаляет его
     * @param toRemove  Integer на удаление
     * @return null если элемент не найдет или объект Number, который был удален
     */
    @SuppressWarnings("unchecked")
    public T remove(Integer toRemove) {
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
            return (T)r;
        }else{
            return null;
        }
    }

    /**
     * Возвращает хэш-код объекта, равный интовому значению первого элемента в коллекции,
     * если коллекция пустая, то выдает 0
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return members.isEmpty() ? 0 : members.get(0).intValue();
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
        boolean flag = true;
        ArrayList<Number> tmp = ((MathBox) obj).getMembers();
        for (int i = 0; i < members.size(); i++){
            if(!members.get(i).equals(tmp.get(i))){
                flag = false;
                break;
            }
        }
        return flag;
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
