package ru.mikheev.kirill.les7.task1;

import java.math.BigInteger;
import java.util.concurrent.Callable;

/**
 * Класс, реализующий интерфейс Callable
 * Считает и возвращает произведение чисел на определенном промежутке
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Calculator implements Callable<BigInteger> {

    /** Начало и конец промежутка, на котором нужно посчитать произведение */
    private Integer start;
    private Integer end;


    /**
     * Конструктор получающий на вход нужный нам промежуток
     * @param start начало промежутка
     * @param end конец промежутка
     */
    public Calculator(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Функция считает произведение чисел на промежутке [start, end] и возвращает объект класса BigInteger
     * @return BigInteger - произвдение всех чисел на промежутке
     */
    @Override
    public BigInteger call() {
        BigInteger answ = new BigInteger(start.toString());
        for (int i = start + 1; i < end; i++){
           answ =  answ.multiply(new BigInteger("" + i));
        }
        return answ;
    }
}
