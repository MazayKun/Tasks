package ru.mikheev.kirill.les2.task3;

/**
 * Enum, реализующий пол человека
 */
public enum Sex {
    MAN("MAN"),
    WOMAN("WOMAN");

    /**
     * Поле текстового значения пола
     */
    String field;

    /**
     * Конструктор заполняющий текстовое поле
     * @param field
     */
    Sex(String field){
        this.field = field;
    }

    /**
     * @return текстовое поле объекта
     */
    @Override
    public String toString() {
        return field;
    }
}
