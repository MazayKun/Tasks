package ru.mikheev.kirill.les2.task3;

public class SelectionSorter implements Sorting {
    @Override
    public void sort(Comparable[] array) throws DoppelgangerException {
        long millis = System.currentTimeMillis();
        Comparable tmp;
        for (int i = 0; i < array.length - 1; i++){
            tmp = array[findMax(i + 1, array)];
            array[findMax(i + 1, array)] = array[i];
            array[i] = tmp;
        }
        System.out.print("time for sort - ");
        System.out.println(System.currentTimeMillis() - millis);
    }

    private int findMax(int startIndex, Comparable[] array) throws DoppelgangerException {
        int tmp = startIndex;
        for (int i = startIndex + 1; i < array.length; i++){
            if(array[i].compareTo(array[tmp]) == 0) {
                throw new DoppelgangerException();
            }
            if(array[i].compareTo(array[tmp]) == 1){
                tmp = i;
            }
        }
        return tmp;
    }
}
