package ru.mikheev.kirill.les8.task2;

import ru.mikheev.kirill.les8.interfaces.ISerializator;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс предназначен для сериализации и десериализации объектов, реализующих интерфейс Serializable
 * Так же сериализует вложенные объекты, если для них это вохможно
 * @author Kirill Mikheev
 * @version 1.0
 */

public class CoolerSerializator implements ISerializator, Serializable {

    /** Список примитивных объектов, которые мы умеем сериализовать по умолчанию*/
    private ArrayList<String> primitives = new ArrayList<>(Arrays.asList( new String[] {"int", "byte", "short", "long", "char", "String"}));


    /**
     * Сериализует переданный объект и сохраняет в файл с заданным именем
     * @param object объект на сериализацию
     * @param file файл, в который сохраняется результат
     */
    @Override
    public void serialize(Object object, String file) {
        try(FileOutputStream fos = new FileOutputStream(file)){
            serializeObject(object, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * Десериализует объект из переданного файла
     * @param file файл, из которого нужно десериализовать объект
     * @return результат десериализации
     */
    @Override
    public Object deSerialize(String file) {
        Object answ = null;
        try (FileInputStream fis = new FileInputStream(file)){
            readToSpace(fis);
            answ = deserializeObject(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Wrong file");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return answ;
    }

    /**
     * Внутренниый класс, который сериализует объекты рекурсивно
     * Таким образом мы можем сериализовать объекты, которые содержат ссылки на другие объекты
     * С одной оговоркой, в конечном счете все должно закончиться примитивами или стринг
     * @param object объект на сериализацию
     * @param fos поток вывода
     * @throws IOException
     * @throws IllegalAccessException
     */
    private void serializeObject(Object object, FileOutputStream fos) throws IOException, IllegalAccessException {
        if(object == null){
            fos.write("null".getBytes());
            return;
        }
        if(!(object instanceof Serializable)){
            System.out.println("Ошибка, объект должен быть Serializable");
            throw new NotSerializableException();
        }


        Class<?> clazz = object.getClass();
        fos.write(("{" + clazz.getName()).getBytes());

        // Всегда первый элемент после полного имени класса будет его serialVersionUID
        try {
            Field version = clazz.getDeclaredField("serialVersionUID");
            version.setAccessible(true);
            fos.write((" " + version.getLong(object)).getBytes());
        } catch (NoSuchFieldException e) {
            System.out.println("Нет уида");
        } catch (IllegalAccessException e) {
            System.out.println("Нет прав");
        }

        // После serialVersionUID будут записаны значения всех полей объекта через пробел
        // Если поле является ссылочным типом, то рекурсивно запускаем сериализацию значения этого поля
        Field[] fields = clazz.getDeclaredFields();
        for(Field tmp : fields){
            if(!tmp.getName().matches(".*serialVersionUID") ) {
                if (Modifier.isPrivate(tmp.getModifiers())) {
                    tmp.setAccessible(true);
                }
                if(!primitives.contains(tmp.getType().getSimpleName())){
                    fos.write(" ".getBytes());
                    serializeObject(tmp.get(object), fos);
                }else {
                    fos.write((" " + tmp.get(object).toString()).getBytes());
                }
            }
        }

        fos.write("}".getBytes());
    }


    /**
     * Десериализует объект начиная с {
     * Делает это рекурсивно и перезапускается, если встречает {
     * @param fis поток ввода из файла
     * @return десериализованный объект
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    private Object deserializeObject(FileInputStream fis) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        Class<?> clazz = Class.forName(readToSpace(fis));
        Object answ = clazz.newInstance();

        Field[] fields = clazz.getDeclaredFields();

        String expression;

        // Проверка на совпадение serialVersionUID
        try {
            Field version = clazz.getDeclaredField("serialVersionUID");
            expression = readToSpace(fis);
            version.setAccessible(true);
            if(version.getLong(answ) != Long.parseLong(expression)){
                System.out.println("serialVersionUID не совпадают");
                throw new InvalidClassException("Wrong serialVersionUID");
            }
        } catch (NoSuchFieldException e) {
            System.out.println("Нет уида");
        }

        // Читаем каждое поле, если встречаем { то это значит, что нужно создать новый объект, запускаем рекурсию
        for (Field tmp : fields){
            if(tmp.getName().matches(".*serialVersionUID")){
                continue;
            }
            expression = readToSpace(fis);
            if(Modifier.isPrivate(tmp.getModifiers())){
                tmp.setAccessible(true);
            }
            if(expression == "{"){
                Object obj = deserializeObject(fis);
                tmp.set(answ, tmp.getType().cast(obj));
                continue;
            }
            switch (tmp.getType().getSimpleName()){
                case "int":{tmp.set(answ, Integer.parseInt(expression)); break;}
                case "byte":{tmp.set(answ, Byte.parseByte(expression)); break;}
                case "short":{tmp.set(answ, Short.parseShort(expression)); break;}
                case "long":{tmp.set(answ, Long.parseLong(expression)); break;}
                case "char":{tmp.set(answ, expression.charAt(0)); break;}
                case "String":{tmp.set(answ, expression);}
            }
        }
        return answ;
    }

    /**
     * Метод считывает из файла информацию до ближайшего пробела и возвращает ее
     * Так же возвращает {
     * @param fis поток ввода
     * @return объект типа String, содержащий информацию до пробела или {
     * @throws IOException
     */
    private String readToSpace(FileInputStream fis) throws IOException {
        StringBuilder sb = new StringBuilder();
        char tmp;
        while ((tmp = (char)fis.read()) != -1){
            if(tmp == '{'){
                return "{";
            }
            if(tmp == ' ' || tmp == '}'){
                break;
            }
            sb.append(tmp);
        }
        return sb.toString();
    }

}
