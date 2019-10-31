package ru.mikheev.kirill.les2;

import ru.mikheev.kirill.les2.task3.*;

public class Test {

    /**
     * Метод мейн для тестирования всех 3 задач по 2 занятию
     */

    public static void main(String[] argc) {
        System.out.println("Puff");
        //HelloWorld hw = new HelloWorld();
        //hw.helloWorld();
        //Generator.generate(1000);
        QuickSorter qs = new QuickSorter();
        SelectionSorter ss = new SelectionSorter();
        PersonFactory pf = new PersonFactory();
        Person[] people = pf.generatePopulation(100);
        pf.printPopulation(people);

        try {
            ss.sort(people);
        } catch (DoppelgangerException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------------------");
        pf.printPopulation(people);
    }
}
