package ru.mikheev.kirill.les2.task3;

/**
 * Не совсем понял задание, поэтому использовал enum, в любом случае мне кажется, что это оптимально.
 * Если критично выполнение тз, то поясните пожалйста задание, и я выполню как надо.
 */
public enum Sex {
    MAN("MAN"),
    WOMAN("WOMAN");

    String field;
    Sex(String field){
        this.field = field;
    }

    @Override
    public String toString() {
        return field;
    }
}
