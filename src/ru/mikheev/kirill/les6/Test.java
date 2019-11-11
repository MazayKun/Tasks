package ru.mikheev.kirill.les6;

import ru.mikheev.kirill.les6.task1.FileSorter;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Test {
    public static void main(String[] args){
        System.out.println("Puff");
        FileSorter fs = new FileSorter();
        fs.sortFile("test.in", "test.out");
    }
}
