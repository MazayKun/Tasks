package ru.mikheev.kirill.les12.task2;

import javassist.CannotCompileException;
import javassist.ClassPool;

/**
 * Утилитарный класс, реализующий метод, выкидывающий java.lang.OutOfMemoryError: Metaspace
 * @author Kirill Mikheev
 * @version 1.0
 */

public class OutOfMemoryMeta {
    /**
     * При запуске выбрасывает java.lang.OutOfMemoryError: Metaspace
     */
    public static void outOfMetaSpace() throws CannotCompileException {
        ClassPool cp = ClassPool.getDefault();
        for (int i = 0; ; i++) {
            Class c = cp.makeClass("ru.mikheev.kirill.generatedclass" + i).toClass();
        }
    }
}
