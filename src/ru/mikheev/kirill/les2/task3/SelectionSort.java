package ru.mikheev.kirill.les2.task3;

public class SelectionSort implements Sorting {
    @Override
    public void sort(Comparable[] array) {
        Comparable tmp;
        for (int i = 0; i < array.length - 1; i++){
            tmp = array[findMax(i + 1, array)];
            array[findMax(i + 1, array)] = array[i];
            array[i] = tmp;
        }
    }

    private int findMax(int startIndex, Comparable[] array) {
        int tmp = startIndex;
        for (int i = startIndex + 1; i < array.length; i++){
            if(array[i].compareTo(array[tmp]) == 1){
                tmp = i;
            }
        }
        return tmp;
    }
}
