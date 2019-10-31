package ru.mikheev.kirill.les2.task3;

import java.util.Random;

/**
 * Класс, позволяющий создавать массив, заполненный случайно сгенерированными элементами класса Person
 */

public class PersonGenerator {

    /** Пулл возможных имен */
    String[] fname = {"a", "b", "c", "d", "e"};

    /**
     * Генерирует массив случайных Person
     * @param n Количество человек в массиве
     * @return Массив Person
     */
    public Person[] generatePopulation(int n) {
        Person[] population = new Person[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int age = random.nextInt(100);
            String name = fname[random.nextInt(fname.length)];
            Sex sex = random.nextInt(2) == 0 ? Sex.MAN : Sex.WOMAN;
            population[i] = new Person(sex, age, name);
        }
        return population;
    }

    /**
     * Выводит на экран toString() для все Person в заданном массиве
     * @param population массив Person
     */
    public void printPopulation(Person[] population){
        for (int i = 0; i < population.length; i++) {
            System.out.println(population[i]);
        }
    }
}
