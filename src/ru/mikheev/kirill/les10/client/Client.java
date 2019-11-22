package ru.mikheev.kirill.les10.client;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Класс клиент
 * Сразу после запуска просит ввести имя и после этого подключается к хосту, который передается в конструктор
 * Сообщения шлются по порту, который прописан в коде, а ловятся по порту на 1 большему
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Client {

    /** Порт для отправки сообщений*/
    private int port;
    /** Адресс хоста*/
    private InetAddress address;
    /** Внутренний сокет, который используется для отправки сообщений на сервер*/
    private DatagramSocket socket;

    /**
     * Конструктор получающий на вход адрес хоста, к которому нужно подключится
     * @param host адрес хоста
     */
    public Client(String host) {
        this.port = 8888;
        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.out.println("WrongHost");
        }
    }

    /**
     * Позволяет измених хоста
     * @param host новый хост
     */
    public void setHost(String host) {
        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.out.println("WrongHost");
        }
    }

    /**
     * ЗАпускает сервер, проводя все нужные приготовления
     */
    public void startClient() {
        CPortListener cpl = new CPortListener(this, port + 1);
        cpl.start();
        readConsole();
        cpl.stopListen();
    }

    /**
     * Подключается к серверу и оповещает его о том, что мы подключились
     * @param username никнейм нового юзера
     * @return сокет, который теперь отвечает за отправку сообщений на сервер
     */
    private DatagramSocket connectToServer( String username) {
        DatagramSocket socket = null;
        try {
            String firstMessage = "/firstmessage$" + username + "$";
            DatagramPacket firstPacket = new DatagramPacket(firstMessage.getBytes(), firstMessage.length(), address, port);
            socket = new DatagramSocket();
            socket.send(firstPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }

    /**
     * Метод, который постоянно запущен, пока работает клиент
     * Он считыввает команды из консоли и распоряжается, что с ними дальше делать
     * Возможные команды:
     * /quit - завершает сеанс и после этого отключается от сервера и завершает работу клиента
     * /unicast $username$ - отправляет личное сообщение пользователю username
     * /help - выводит список доступных команд
     */
    private void readConsole() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = in.nextLine();
        socket = connectToServer(name);
        while(true){
            String message = in.nextLine();
            if(message.equals("/quit")){
                break;
            }
            if(message.equals("/help")){
                System.out.println("/quit - leave chat\n/unicast $username$ - private message");
                continue;
            }
            sendMessage(message);
        }
        sendMessage("$left");
    }

    /**
     * Отправляет данное сообщенеи на сервер
     * @param message текст сообщения
     */
    public void sendMessage(String message) {
        try {
            DatagramPacket firstPacket = new DatagramPacket(message.getBytes(), message.length(), address, port);
            socket.send(firstPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Выводит сообщение, которое пришлос в данном пакете в консоль
     * @param packet пакет, полученный от сервера
     */
    public void showMessage(DatagramPacket packet) {
        try {
            byte[] data = Arrays.copyOfRange(packet.getData(),0, packet.getLength());
            String message = new String(data, "UTF-8");
            System.out.println(message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
