package ru.mikheev.kirill.les3;

import ru.mikheev.kirill.les3.task1.MathBox;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;

/**
 * @author Kirill Mikheev
 * @version 1.0
 *
 * Класс для тестирования задач по 3 занятию
 */

public class Test {
    public static void main(String[] argc) {
        System.out.println("Puff");

        Long a = 10l;
        Double b = 10d;
        Number[] arr = {a, b};
        ru.mikheev.kirill.les3.task3.MathBox mathBox = new ru.mikheev.kirill.les3.task3.MathBox(arr);
        System.out.println(mathBox.toString());
        System.out.println(mathBox.summator().doubleValue());
        mathBox.splitter(a);
        mathBox.dump();
        System.out.println(mathBox.toString());
        mathBox.remove(1);
        System.out.println(mathBox.toString());

    }
}
