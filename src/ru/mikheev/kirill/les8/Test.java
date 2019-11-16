package ru.mikheev.kirill.les8;

import ru.mikheev.kirill.les8.task1.Serializator;

/**
 * Класс мейн для тестирования задачи по 8 заданию
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Test {
    public static void main(String[] args) {
        System.out.println("Puff");
        TestClass tc = new TestClass();
        tc.testInt = 456;
        Serializator s = new Serializator();
        s.serialize(tc, "serial2.msf");
        TestClass answ = (TestClass) s.deSerialize("serial2.msf");
        System.out.println(answ.testInt + answ.testString + answ.testChar);
    }
}
