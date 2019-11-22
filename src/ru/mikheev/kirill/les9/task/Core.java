package ru.mikheev.kirill.les9.task;

import javax.tools.*;
import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;


/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Core{

    public static void makeNewClass() throws IOException {
        Scanner in = new Scanner(System.in);
        try(FileOutputStream fos = new FileOutputStream("./src/ru/mikheev/kirill/les9/task/WorkerImpl.java")){
            fos.write(("package ru.mikheev.kirill.les9.task;\n" +
                    "import ru.mikheev.kirill.les9.task.Worker;" +
                    "public class WorkerImpl implements Worker{\n" +
                    "\tpublic void doWork(){\n").getBytes());
            String input = in.nextLine();
            while(!input.isEmpty()){
                fos.write(("\t\t" + input + "\n").getBytes());
                input = in.nextLine();
            }
            fos.write("\t}\n}".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        in.close();
        compile();
    }

    private static void compile() throws IOException {
        //Runtime.getRuntime().exec();
    }

}
