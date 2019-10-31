package ru.mikheev.kirill.les2.task3;

/**
 * Общий интерфейс сортировок
 */
public interface Sorting  {
    /**
     * Метод, сортирующий массив элементов Comparable
     * @param array
     * @throws DoppelgangerException выбрасывает если найдены 2 человека с одинаковыми возрастом и именем
     */
    void sort(Comparable[] array) throws DoppelgangerException;
}
