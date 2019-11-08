package ru.mikheev.kirill.les2.task3;

/**
 * Класс, описывающий модель отдельно взятого человека
 */

public class Person implements Comparable<Person>{

    /** Поле, которое хранит пол человека */
    private Sex sex;
    /** Поле, которое хранит возраст человека */
    private Integer age;
    /** Поле, которое хранит пол человека */
    private String name;

    /**
     * Конструктор принимает все основные поля класса и сохраняет их
     * @param sex
     * @param age
     * @param name
     */
    public Person(Sex sex, Integer age, String name) {
        this.sex = sex;
        this.age = age;
        this.name = name;
    }

    /**
     * Метод сравнивает одного человека с другим по принципу MAN > WOMAN, при равенстве сравнивается возраст,
     * если и здесь равенство, то сравниваются имена в алфавитном порядке, при их равенстве оба человека равны
     * @param person
     * @return 1 если this больше person, 0 при равенстве, -1 если this меньше person
     */
    @Override
    public int compareTo(Person person) {
        if(person.getSex().compareTo(this.sex) != 0 ){
            return this.getSex().compareTo(person.sex);
        }
        if(this.age.compareTo(person.getAge()) != 0){
            return person.age.compareTo(this.getAge());
        }
        if(person.getName().compareTo(this.name) != 0){
            return person.getName().compareTo(this.name);
        }
        return 0;
    }

    /**
     * Стандартный геттер
     * @return пол человека
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Стандартный геттер
     * @return возраст человека
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Стандартный геттер
     * @return имя человека
     */
    public String getName() {
        return name;
    }

    /**
     * @return перечисленные через пробел поля sex, age, name
     */
    @Override
    public String toString() {
        return "" + sex + " " + age + " " + name;
    }
}
