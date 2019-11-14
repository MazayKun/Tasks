package ru.mikheev.kirill.les7.task1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Объект, который позволяет считать факториалы чисел в массиве
 * @author Kirill Mikheev
 * @version 1.0
 */

public class ThreadPull {

    /**
     * Метод получает на вход массив чисел и количетсво потоков
     * Считает для каждого элемента в массиве его факторил, распараллеливая задачу на заданное количество потоков
     * @param array массив чисел на подсчет факториалов
     * @param threadCount желаемое количетсво потоков
     * @return массив объектов BigInteger, в котором храняться факториалы
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public BigInteger[] calculateFactorials(Integer[] array, int threadCount) throws ExecutionException, InterruptedException {

        BigInteger[] answ = new BigInteger[array.length];

        for (int i = 0; i < array.length; i++){
            answ[i] = calculateFactorial(array[i], threadCount);
        }

        return answ;
    }

    /**
     * Приватный метод, который распараллеливает вычисление факториала заданного числа на заданное количество потоков
     * Если число меньше количетсва потоков, то все вычисления производятся в одном потоке
     * @param number само число
     * @param threadCount количество потоков
     * @return объект BigInteger - ответ
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private BigInteger calculateFactorial(Integer number, int threadCount) throws ExecutionException, InterruptedException {
        if(number == 0) {
            return new BigInteger("1");
        }else{
            if(number <= threadCount){
                FutureTask<BigInteger> answ = new FutureTask<>(new Calculator(1, number + 1));
                new Thread(answ).start();
                return answ.get();
            }
            else {
                ArrayList<FutureTask<BigInteger>> futures = new ArrayList<>();
                for (int i = 0; i < threadCount; i++) {
                    FutureTask<BigInteger> tmp = new FutureTask<>(new Calculator((i == 0) ? 1 : number / threadCount * i, (i == threadCount - 1) ? number + 1 : number / threadCount * (i + 1)));
                    new Thread(tmp).start();
                    futures.add(tmp);
                }
                BigInteger answ = new BigInteger("1");
                for (FutureTask<BigInteger> tmp : futures) {
                    answ = answ.multiply(tmp.get());
                }
                return answ;
            }
        }
    }
}
