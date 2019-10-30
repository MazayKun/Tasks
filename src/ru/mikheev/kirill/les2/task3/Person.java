package ru.mikheev.kirill.les2.task3;

public class Person implements Comparable<Person>{

    public Sex sex;
    public Integer age;
    public String name;

    public Person(Sex sex, Integer age, String name) {
        this.sex = sex;
        this.age = age;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "" + sex + " " + age + " " + name;
    }
}
