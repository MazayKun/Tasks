package ru.mikheev.kirill.les9.task;

import java.io.*;

/**
 * Класс должен был считывать код из консоли, заливать его в файл и компилировать файл, после
 * он должен был загружать класс в рантайме и отдавать ссылку на него тому, кто вызвал
 * Однако из-за проблем с компилятором я не смог реализовать нормальную компиляцию, так что использую уже готовую
 * реализацию
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Core{

    /**
     * Метод использует класс лоадер для создания ээкзмепляра класса
     * @return объект Class
     * @throws IOException
     */
    public static Class<?> makeNewClass() throws IOException {
        /*Scanner in = new Scanner(System.in);
        try(FileOutputStream fos = new FileOutputStream("./src/ru/mikheev/kirill/les9/task/WorkerImpl.java")){
            fos.write(("import ru.mikheev.kirill.les9.task.Worker;\n" +

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
        compile();*/
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> answ = null;
        try {
            answ = myClassLoader.loadClass("ru.mikheev.kirill.les9.task.WorkerImpl");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return answ;
    }

    /**
     * Старый метод, который компилировал файлы в рантайме
     */
    private static void compile(){
        try {
            String path1 = "C:/Users/kir_9/IdeaProjects/Tasks/";
            String path2 = path1 + "src/ru/mikheev/kirill/les9/task/";
            Runtime.getRuntime().exec("javac " + path1 + "Worker.java " + path2 + "WorkerImpl.java");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
