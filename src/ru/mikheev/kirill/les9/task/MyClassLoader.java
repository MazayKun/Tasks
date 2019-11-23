package ru.mikheev.kirill.les9.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс наследованнй от ClassLoader
 * Предназначен для загрузки класса ru.mikheev.kirill.les9.task.WorkerImpl
 * @author Kirill Mikheev
 * @version 1.0
 */

public class MyClassLoader extends ClassLoader {

    /**
     * Метод загружает класс, имя которого мы передали
     * @param name строковое имя класса
     * @return ссылка на объект Class
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("ru.mikheev.kirill.les9.task.WorkerImpl".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    /**
     * Метод находит класс, имя которого ему передано
     * @param name имя класса
     * @return ссылка на объект Class
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if("ru.mikheev.kirill.les9.task.WorkerImpl".equals(name)){
            try{
                byte[] bytes = Files.readAllBytes(Paths.get("./out/production/Tasks/ru/mikheev/kirill/les9/task/WorkerImpl.class"));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}
