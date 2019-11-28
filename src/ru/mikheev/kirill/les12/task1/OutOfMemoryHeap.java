package ru.mikheev.kirill.les12.task1;

/**
 * Утилитарный класс, реализующий метод, выкидывающий java.lang.OutOfMemoryError: Java heap space
 * @author Kirill Mikheev
 * @version 1.0
 */

public class OutOfMemoryHeap {
    /**
     * При запуске выбрасывает java.lang.OutOfMemoryError: Java heap space
     */
    public static void outOfHeap() {
        int arrsize = 1;
        while(true){
            int a[] = new int[arrsize];
            for(int i = 0; i < arrsize; i++){
                a[i] = i;
            }
            Runtime.getRuntime().freeMemory();
            System.out.println("another iter " + arrsize);
            arrsize *= 10;
        }
    }
}
