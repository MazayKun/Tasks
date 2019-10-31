package ru.mikheev.kirill.les2.task2;

import java.util.Random;

public class Generator {

    public void generate() {

        int N = 1000;
        Random rnd = new Random();
        int[] k = new int[N];

        for (int i = 0; i < N; i++){
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
