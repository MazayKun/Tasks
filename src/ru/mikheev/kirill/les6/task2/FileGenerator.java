package ru.mikheev.kirill.les6.task2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Класс содержит в себе методы, обеспечивающие генерацию файлов на основе заданных значений
 * @author Kirill Mikheev
 * @version 1.0
 */

public class FileGenerator {

    /** Массив возможных окончаний предложения */
    private char[] endSym = {'.', '!', '?'};

    /**
     * Метод генерирует в заданной директории n файлов типа {test0.out, test1.out ... testn.out}
     * каждый из этих файлов будет содержать количество слов равное size
     * файлы будут содержать абзацы, в каждом из которых 1 <= N <= 20  предложений, в каждом предложении 1 <= N <= 15 слов
     * в каждом слове 1 <= N <= 15 букв, так же с вероятностью probability вместо сгенерированного слова может вставиться слово из массива words
     * @param path директория, в которой будут созданы файлы
     * @param n количество файлов
     * @param size количество слов в каждом файле
     * @param words массив шаблонов слов, которые могут встретиться в файле
     * @param probability вероятность в процентах (0..100) того, что будет вставлено слово из массива words
     */
    public void getFiles(String path, int n, int size, String[] words, int probability) {

        Random random = new Random();
        boolean onlyArray = (probability >= 100 || probability < 0);

        for (int j = 0 ; j < n; j++){

            StringBuilder answ = new StringBuilder();

            try (FileOutputStream fos = new FileOutputStream(path + "test" + j + ".out")){

                while(size > 0) {
                    int paragraphCount = random.nextInt(19) + 1;
                    for (int i = 0; i < paragraphCount; i++) {
                        if (i == 0) {
                            answ.append("\t");
                        }
                        int wordCount = random.nextInt(15) + 1;
                        if (size < 15) {
                            wordCount = size;
                        }
                        size -= wordCount;
                        for (int k = 0; k < wordCount; k++) {
                            String tmp = (onlyArray || random.nextInt(100) < probability) ? words[random.nextInt(words.length)] : generateWord(random.nextInt(15) + 1);
                            if (k == 0) {
                                tmp = makeWordFirst(tmp);
                            }
                            answ.append(tmp + ((k != wordCount - 1) ? " " : endSym[random.nextInt(endSym.length)] + " "));
                        }
                        if (size < 1) {
                            break;
                        }
                        if (i == paragraphCount - 1) {
                            answ.append("\n");
                        }
                    }
                }

                fos.write(answ.toString().getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Приватная функция, которая генерирует слово заданной длины
     * @param length длина слова
     * @return случайное слово заданной длины
     */
    private String generateWord(int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0 ; i < length; i++){
            sb.append((char)(random.nextInt(26) + 97));
        }
        return sb.toString();
    }

    /**
     * Приватная функция, которая делает первую букву слова заглавной
     * @param word слово, первую букву которого нужно сделать заглавной
     * @return возвращает измененное слово
     */
    private String makeWordFirst(String word){
        word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
        return word;
    }
}
