package ru.mikheev.kirill.les2.task2;

import java.util.Random;

/**
 * Абстрактный класс, предоставляющий статичный метод для генерации набора случайных чисел
 * и проверки возможности взятия из них корня, если корень взять можно, то проверяется, является ли результат целым,
 * если да, то число выводится на экран
 */

public abstract class Generator {

    /**
     * @param n количество чисел для генерации
     */
    public static void generate(int n) {
        Random rnd = new Random();
        int[] k = new int[n];

        for (int i = 0; i < n; i++){
            k[i] = rnd.nextInt();

            //если использовать throws вместо try catch, то программа всегда будет крашиться

            try {
                if(k[i] < 0){
                    throw new ArithmeticException();
                }

                double q = Math.sqrt(k[i]);

                if(Math.pow(Math.round(q), 2) == k[i]){
                    System.out.println(k[i]);
                }
            }catch (ArithmeticException e){
            }
        }
    }

}
