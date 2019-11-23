package ru.mikheev.kirill.les9;

import ru.mikheev.kirill.les9.task.Core;
import ru.mikheev.kirill.les9.task.Worker;

import java.io.IOException;

/**
 * Класс для тестирования 9 задания
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Test {
    public static void main(String[] args) throws IOException {
        Class<?> newClass = Core.makeNewClass();
        try {
            Worker newInstance = (Worker) newClass.newInstance();
            newInstance.doWork();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
