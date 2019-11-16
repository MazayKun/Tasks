package ru.mikheev.kirill.les8;

import java.io.Serializable;

/**
 * Класс для тестирования улучшенных сериализаторов
 * @author Kirill Mikheev
 * @version 1.0
 */

public class CoolerTestClass implements Serializable {
    private static final long serialVersionUID = -10L;
    public int testInt = 10;
    public short testShort = 10;
    public long testLong = 10;
    public char testChar = 'a';
    private byte testByte = 1;
    public String testString = "PIP";
    public TestClass testClass = new TestClass();
}
