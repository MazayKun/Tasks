package ru.mikheev.kirill.les12;

import javassist.CannotCompileException;
import ru.mikheev.kirill.les12.task1.OutOfMemoryHeap;
import ru.mikheev.kirill.les12.task2.OutOfMemoryMeta;


/**
 * Класс для тестирования заданий по занятию 12
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Test {
    public static void main(String[] args) throws CannotCompileException {
        //OutOfMemoryHeap.outOfHeap();
        OutOfMemoryMeta.outOfMetaSpace();
    }
}
