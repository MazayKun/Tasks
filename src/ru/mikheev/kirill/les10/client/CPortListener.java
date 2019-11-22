package ru.mikheev.kirill.les10.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * Поток, слушающий приходящие из сети сообщения
 * @author Kirill Mikheev
 * @version 1.0
 */

public class CPortListener extends Thread {

    /** Порт, который нужно прослушивать*/
    private int port;
    /** Ссылка на приложение клиент, к которому привязан поток*/
    private Client client;
    /** внутренняя переменная, отображающая, работает ли поток*/
    private boolean isRunning;
    /** Пакет данных, в который сохранятся пришедшие данные*/
    private DatagramPacket remoteData;

    /**
     * Креструктор получающий на вход ссылку на клиента, который запустил поток, и порт, который будет прослушиваться
     * @param client ссылка на клиента
     * @param port порт для прослушивания
     */
    public CPortListener(Client client, int port) {
        this.client = client;
        this.port = port;
        byte[] buffer = new byte[65535];
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
     * Метод, прослушивающий порт
     * Таймаут сокета установлен на 5 секунд, так что поток после завершения еще некоторое время будет работать
     * Если какое то сообщение поймано, то пересылает его клиенту
     */
    @Override
    public void run() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(port);
            socket.setSoTimeout(5000);
            while (isRunning) {
                try {
                    socket.receive(remoteData);
                    client.showMessage(remoteData);
                }catch (SocketTimeoutException e){}
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

    }

    /**
     * запускает процесс остановки потока
     */
    public void stopListen(){
        isRunning = false;
    }
}
