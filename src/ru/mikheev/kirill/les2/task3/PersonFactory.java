package ru.mikheev.kirill.les2.task3;

import java.util.Random;

public class PersonFactory {

    String[] fname = {"a", "b", "c", "d", "e"};

    public Person[] generatePopulation(int N) {
        Person[] population = new Person[N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            int age = random.nextInt(100);
            String name = fname[random.nextInt(fname.length)];
            Sex sex = random.nextInt(2) == 0 ? Sex.MAN : Sex.WOMEN;
            population[i] = new Person(sex, age, name);
        }
        return population;
    }

    public void printPopulation(Person[] population){
        for (int i = 0; i < population.length; i++) {
            System.out.println(population[i]);
        }
    }
}
