package ru.mikheev.kirill.les2.task3;

/**
 * Класс, описывающий модель отдельно взятого человека
 */

public class Person implements Comparable<Person>{

    /** Поле, которое хранит пол человека */
    public Sex sex;
    /** Поле, которое хранит возраст человека */
    public Integer age;
    /** Поле, которое хранит пол человека */
    public String name;

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
        if(person.sex.compareTo(this.sex) != 0 ){
            return person.sex.compareTo(this.sex);
        }
        if(this.age.compareTo(person.age) != 0){
            return this.age.compareTo(person.age);
        }
        if(person.name.compareTo(this.name) != 0){
            return person.name.compareTo(this.name);
        }
        return 0;
    }

    /**
     * @return перечисленные через пробел поля sex, age, name
     */
    @Override
    public String toString() {
        return "" + sex + " " + age + " " + name;
    }
}
