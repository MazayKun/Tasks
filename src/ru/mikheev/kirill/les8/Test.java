package ru.mikheev.kirill.les8;

import ru.mikheev.kirill.les8.task1.Serializator;
import ru.mikheev.kirill.les8.task2.CoolerSerializator;

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
        s.serialize(tc, "serial1.msf");
        TestClass answ = (TestClass) s.deSerialize("serial1.msf");
        System.out.println(answ.testInt);

        CoolerTestClass ctc = new CoolerTestClass();
        ctc.testClass = answ;
        CoolerSerializator cs = new CoolerSerializator();
        cs.serialize(ctc, "serial2.msf");
        CoolerTestClass coolerAnsw = (CoolerTestClass) cs.deSerialize("serial2.msf");
        System.out.println(coolerAnsw.testClass.testInt);

    }
}
