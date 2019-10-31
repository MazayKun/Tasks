package ru.mikheev.kirill.les2.task3;


public class QuickSorter implements Sorting {

    @Override
    public void sort(Comparable[] array) throws DoppelgangerException {
        long millis = System.currentTimeMillis();
        int startIndex = 0;
        int endIndex = array.length - 1;
        doSort(startIndex, endIndex, array);
        System.out.print("time for sort - ");
        System.out.println(System.currentTimeMillis() - millis);
    }

    private void doSort(int start, int end, Comparable[] array) throws DoppelgangerException{
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i].compareTo(array[cur]) >= 0)) {
                if(array[i].compareTo(array[cur]) == 0){
                    throw new DoppelgangerException();
                }
                i++;
            }
            while (j > cur && (array[cur].compareTo(array[j]) >= 0)) {
                if(array[j].compareTo(array[cur]) == 0){
                    throw new DoppelgangerException();
                }
                j--;
            }
            if (i < j) {
                Comparable temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur) {
                    cur = j;
                }
                else if (j == cur) {
                    cur = i;
                }
            }
        }
        doSort(start, cur, array);
        doSort(cur + 1, end, array);
    }
}
