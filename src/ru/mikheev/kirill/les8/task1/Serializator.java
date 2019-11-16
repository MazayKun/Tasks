package ru.mikheev.kirill.les8.task1;

import ru.mikheev.kirill.les8.interfaces.ISerializator;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Класс предназначен для сериализации и десериализации объектов, реализующих интерфейс Serializable
 * Объекты могут содержать только примитивные поля и String-поля
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Serializator implements ISerializator {

    /**
     * Сериализует переданный объект и сохраняет в файл с заданным именем
     * @param object объект на сериализацию
     * @param file файл, в который сохраняется результат
     */
    @Override
    public void serialize(Object object, String file) {
        if(!(object instanceof Serializable)){
            System.out.println("Ошибка, объект должен быть Serializable");
            return;
        }

        try(FileOutputStream fos = new FileOutputStream(file)){

            Class<?> clazz = object.getClass();
            fos.write(("{" + clazz.getName()).getBytes());

            // Всегда первый элемент после полного имени класса будет его serialVersionUID
            try {
                Field version = clazz.getDeclaredField("serialVersionUID");
                version.setAccessible(true);
                fos.write((" " + version.getLong(object)).getBytes());
            } catch (NoSuchFieldException e) {
            } catch (IllegalAccessException e) {
            }

            // После serialVersionUID будут записаны значения всех полей объекта через пробел
            Field[] fields = clazz.getDeclaredFields();
            for(Field tmp : fields){
                if(!tmp.getName().matches(".*serialVersionUID") ) {
                    if (Modifier.isPrivate(tmp.getModifiers())) {
                        tmp.setAccessible(true);
                    }
                    fos.write((" " + tmp.get(object).toString()).getBytes());
                }
            }

            fos.write("}".getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {

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
            Class<?> clazz = Class.forName(readToSpace(fis));
            answ = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            String expression;
            try {
                Field version = clazz.getDeclaredField("serialVersionUID");
                expression = readToSpace(fis);
                version.setAccessible(true);
                if(version.getLong(answ) != Long.parseLong(expression)){
                    System.out.println("serialVersionUID не совпадают");
                    return null;
                }
            } catch (NoSuchFieldException e) {
            }

            for (Field tmp : fields){
                if(tmp.getName().matches(".*serialVersionUID")){
                    continue;
                }
                expression = readToSpace(fis);
                if(Modifier.isPrivate(tmp.getModifiers())){
                    tmp.setAccessible(true);
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
     * Метод считывает из файла информацию до ближайшего пробела и возвращает ее
     * @param fis поток ввода
     * @return объект типа String, содержащий информацию до пробела
     * @throws IOException
     */
    private String readToSpace(FileInputStream fis) throws IOException {
        StringBuilder sb = new StringBuilder();
        char tmp;
        while ((tmp = (char)fis.read()) != -1){
            if(tmp == '{'){
                continue;
            }
            if(tmp == ' ' || tmp == '}'){
                break;
            }
            sb.append(tmp);
        }
        return sb.toString();
    }

}
