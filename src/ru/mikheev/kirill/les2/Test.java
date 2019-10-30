package ru.mikheev.kirill.les2;

import ru.mikheev.kirill.les2.task1.HelloWorld;
import ru.mikheev.kirill.les2.task2.Generator;
import ru.mikheev.kirill.les2.task3.Person;
import ru.mikheev.kirill.les2.task3.PersonFactory;
import ru.mikheev.kirill.les2.task3.QuickSort;
import ru.mikheev.kirill.les2.task3.SelectionSort;

public class Test {
    public static void main(String[] argc) {
        System.out.println("Puff");
        //HelloWorld hw = new HelloWorld();
        //hw.helloWorld();
        //Generator generator = new Generator();
        //generator.generate();
        QuickSort qs = new QuickSort();
        SelectionSort ss = new SelectionSort();
        PersonFactory pf = new PersonFactory();
        Person[] people = pf.generatePopulation(100);
        pf.printPopulation(people);
        ss.sort(people);
        System.out.printf("-----------------------------------------------");
        pf.printPopulation(people);
    }
}