package ru.mikheev.kirill.les7;


import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

/**
 * Класс мейн для тестирования задачи по 7 заданию
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Puff");

        Integer[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 40, 50};

        ru.mikheev.kirill.les7.task1.ThreadPull threadPull = new ru.mikheev.kirill.les7.task1.ThreadPull();
        for (BigInteger tmp : threadPull.calculateFactorials(arr,3)){
            System.out.println(tmp.toString());
        }

        ru.mikheev.kirill.les7.task1_v2.ThreadPull threadPull2 = new ru.mikheev.kirill.les7.task1_v2.ThreadPull();
        for (BigInteger tmp : threadPull2.calculateFactorials(arr,3)){
            System.out.println(tmp.toString());
        }
    }
}
