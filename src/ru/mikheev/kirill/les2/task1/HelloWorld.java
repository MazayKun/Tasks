package ru.mikheev.kirill.les2.task1;

import java.io.FileNotFoundException;

public class HelloWorld {

    public void helloWorld() {

        Integer nullRef = null;

        try {
            System.out.println(nullRef.intValue());
        }catch(NullPointerException e){
            System.out.println("Caught NullPointerException");
        }

        int[] fiveElementArray = new int[5];

        try {
            for (int i = 0; i < 6; i++){
                System.out.print(fiveElementArray[i] + " ");
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("\nCaught ArrayIndexOutOfBoundsException");
        }

        try {
            throw new FileNotFoundException();
        }catch (FileNotFoundException e){
            System.out.println("FileNotFoundException");
        }
    }

}
