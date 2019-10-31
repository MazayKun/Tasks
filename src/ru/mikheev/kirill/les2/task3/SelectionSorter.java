package ru.mikheev.kirill.les2.task3;

/**
 * Класс, производящий сортировку выбором
 */

public class SelectionSorter implements Sorting {

    /**
     * Метод, сортирующий массив элементов Comparable
     * @param array
     * @throws DoppelgangerException выбрасывает если найдены 2 человека с одинаковыми возрастом и именем
     */
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

    /**
     * Метод ищет максимальный элемент массива начиная со startIndex
     * @param startIndex
     * @param array
     * @return Индекс элемента в массиве
     * @throws DoppelgangerException выбрасывает если найдены 2 человека с одинаковыми возрастом и именем
     */
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
