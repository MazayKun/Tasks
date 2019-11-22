package ru.mikheev.kirill.les10.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
import java.util.Arrays;

/**
 * Объект прослушивает данный ему порт и передает полученные данные в сервер, к которому он привязан
 * @author Kirill Mikheev
 * @version 1.0
 */

public class SPortListener extends Thread {

    /** Ссылка на сервер, к которому привязан данный поток*/
    private Server server;
    /** Внутренняя переменная, которая обозначает выполняется ли поток*/
    private boolean isRunning;
    /** Пакет, в который сохраняются полученные данные*/
    private DatagramPacket remoteData;
    /** Буфффер, который вложен в пакет данных (после каждой обработки нового сообщения буффер очищается)*/
    private byte[] buffer;
    /** Порт, который будет прослушивать поток*/
    int port;

    /**
     * Конструктор, который на вход принимает ссылку на сервер, к которому прикреплен поток, и порт, который нужно прослушивать
     * @param server ссылка на сервер
     * @param port порт, который нужно прослушивать
     */
    public SPortListener(Server server, int port) {
        this.server = server;
        this.port = port;
        buffer = new byte[65000];
        remoteData = new DatagramPacket(buffer, buffer.length);
    }

    /**
     * Метод, запускающий поток
     */
    @Override
    public synchronized void start() {
        isRunning = true;
        super.start();
    }

    /**
     * Метод, который опрашивает порт (Таймаут стоит 5 секунд, так что при завершении потока, он еще некоторое время будет работать)
     */
    @Override
    public void run() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(port);
            socket.setSoTimeout(5000);
            while (isRunning){
                Arrays.fill(buffer, (byte)0);
                remoteData.setData(buffer);
                try {
                   socket.receive(remoteData);
                   server.readMessage(remoteData);
                }catch (SocketTimeoutException e){}
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

    /**
     * Метод запустит процесс завершения работы потока
     */
    public void stopListen(){
        isRunning = false;
    }
}
