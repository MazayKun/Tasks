package ru.mikheev.kirill.les7.task1;

import java.math.BigInteger;
import java.util.concurrent.Callable;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Calculator implements Callable<BigInteger> {

    private Integer start;
    private Integer end;

    public Calculator(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public BigInteger call() {
        BigInteger answ = new BigInteger(start.toString());
        for (int i = start + 1; i < end; i++){
           answ =  answ.multiply(new BigInteger("" + i));
        }
        return answ;
    }
}
