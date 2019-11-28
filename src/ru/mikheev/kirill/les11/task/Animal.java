package ru.mikheev.kirill.les11.task;

import ru.mikheev.kirill.les2.task3.Person;
import ru.mikheev.kirill.les2.task3.PersonGenerator;

import java.util.Random;

/**
 * Класс, отображающий модель записи информации об одном конкретном животном
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Animal implements Comparable<Animal>{

    /** Счетчик уникальных айди (первое созданное животное будет иметь id = 1)*/
    private static Integer lastId = 0;
    /** Пул имен для генерации случайного животного */
    private static String[] fname = {"abc", "def", "ghi" , "jkl"};


    /** Уникальный id животного*/
    private final Integer id;
    /** Кличка животного*/
    private String name;
    /** Владелец животного животного (объект класса Person из задания со 2 занятия)*/
    private Person owner;
    /** Вес животного*/
    private Double weight;

    /**
     * Конструктор, создающий животное по заданным признакам и выделяющий ему уникальный id
     * @param name кличка животного
     * @param owner владелец животного (объект класса Person из задания со 2 занятия)
     * @param weight вес животного
     */
    public Animal(String name, Person owner, Double weight){
        Animal.lastId++;
        this.id = lastId.intValue();
        this.name = name;
        this.owner = owner;
        this.weight = weight;
    }

    /**
     * Возвращает id данного животного
     * @return id животного
     */
    public Integer getId() {
        return id;
    }

    /**
     * Возвращает имя
     * @return имя животного
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает ссылку на владельца данного животного
     * @return владелец животного (объект класса Person из задания со 2 занятия)
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Возвращает вес животного
     * @return вес животного
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Возвращает id последнего созданного животного
     * @return id последнего созданного животного
     */
    public static Integer getLastId() {
        return lastId;
    }

    /**
     * Задает животному новую кличку
     * @param name новая кличка
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Задает животному нового хозяина
     * @param owner ссылка на наого хозяина (объект класса Person из задания со 2 занятия)
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * Задает животному новый вес
     * @param weight новый вес
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * Сначала проверяется равен ли obj null, если равен, то возвращается false
     * Далее проверяется, является ли obj объектом класса Animal или его наследника, если нет, то возвращает false
     * Потом сравнивается id этих объектов, при равенствен возвращает true
     * И в конце сравнивает объекты при помощи compareTo() и возвращает true, если в итоге будет 0, иначе false
     * @param obj объект для сравнения
     * @return true если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(!(obj instanceof Animal)){
            return false;
        }
        if(this.id.compareTo(((Animal)obj).getId()) == 0){
            return true;
        }
        return this.compareTo((Animal)obj) == 0;
    }

    /**
     * Возвращает хэш-код данного животного
     * @return уникальный id данного животного
     */
    @Override
    public int hashCode() {
        return this.id;
    }

    /**
     * Сравнивает двух Animal
     * Animal всегда больше null
     * Сравниваются их хозяева (если не 0, то выдает результат сравнения)
     * При равенствен хозяев сравниваются клички животных (если не 0, то выдает результат сравнения)
     * При равенствен кличек выдется результат сравнения веса животных
     * @param animal объект для сравнения с данным
     * @return 1 если this больше, -1 если this меньше, 0 если объекты равны
     */
    @Override
    public int compareTo(Animal animal) {
        int tmp;
        if(animal == null){
            return 1;
        }
        tmp = this.getOwner().compareTo(animal.getOwner());
        if(tmp != 0){
            return tmp;
        }
        tmp = this.getName().compareTo(animal.getName());
        if(tmp != 0){
            return tmp;
        }
        return this.getWeight().compareTo(animal.getWeight());
    }

    /**
     * Выдает метод toString хозяина, потом имя животного и в конце вес животного
     * @return общую информацию об объекте в формате строки
     */
    @Override
    public String toString() {
        return "owner : " + owner.toString() + " | name : " + name + " | weight : " + weight.toString();
    }

    /**
     * Метод для случайной генерации животного
     * @return Случайно сгенерированное животное
     */
    public static Animal generateAnimal() {
        Random random = new Random();
        Person owner = PersonGenerator.generatePerson();
        String name = fname[random.nextInt(fname.length)];
        Double weight = random.nextDouble() * 100;
        return new Animal(name, owner, weight);
    }
}
