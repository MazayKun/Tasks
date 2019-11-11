package ru.mikheev.kirill.les6;

import ru.mikheev.kirill.les6.task1.FileSorter;
import ru.mikheev.kirill.les6.task2.FileGenerator;

/**
 * Файл мейн для тестирования заданий по 6 занятию
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Test {
    public static void main(String[] args){
        System.out.println("Puff");
        //FileSorter fs = new FileSorter();
        //fs.sortFile("test.in", "test.out");
        FileGenerator fg = new FileGenerator();
        String[] words = {"kek", "lol", "fif", "dod"};
        fg.getFiles("", 1, 500, words, 50);
    }
}
