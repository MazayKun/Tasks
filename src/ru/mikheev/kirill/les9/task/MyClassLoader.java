package ru.mikheev.kirill.les9.task;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("WorkerImpl".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

}
