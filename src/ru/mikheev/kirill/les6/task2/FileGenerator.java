package ru.mikheev.kirill.les6.task2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public class FileGenerator {

    private char[] endSym = {'.', '!', '?'};

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

    private String generateWord(int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0 ; i < length; i++){
            sb.append(random.nextInt(26) + 97);
        }
        return sb.toString();
    }

    private String makeWordFirst(String word){
        word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
        return word;
    }
}
