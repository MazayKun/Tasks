package ru.mikheev.kirill.les6.task1;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс содержит в себе один метод, предназначенный для сортировки заданного файла.
 * По поводу DataInputStream.getUTF() появился вопрос, постараюсь не забыть задать его на консультации
 * @author Kirill Mikheev
 * @version 1.0
 */

public class FileSorter {
    /**
     * Метод получает на вход имена двух файлов, считывает слова через пробел из первого файла,
     * убирает дубликаты, сортирует их и кладет во второй файл
     * @param fileInName имя файла, из которого будут прочитаны слова
     * @param fileOutName имя файла, в который будет записан результат
     */
    public void sortFile(String fileInName, String fileOutName){
        try (FileInputStream fis = new FileInputStream(fileInName) ; FileOutputStream fos = new FileOutputStream(fileOutName)){
            Set<String> answ = new TreeSet<>();
            StringBuilder word = new StringBuilder();
            int tmp;
            while ((tmp = fis.read()) != -1){
                if(tmp != ' '){
                    word.append((char)tmp);
                }else{
                    answ.add(word.toString());
                    word.delete(0, word.length());
                }
            }
            answ.add(word.toString());
            word.delete(0, word.length());
            for(String toRead : answ){
                fos.write(toRead.getBytes());
                fos.write(' ');
            }
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла не существует");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
