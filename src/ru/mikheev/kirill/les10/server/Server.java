package ru.mikheev.kirill.les10.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс сервер
 * К мнему подключаются все клиенты, он рассылает всем сообщения и содержит адреса всех клиентов
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Server {

    /** Сокет, с помощью которого рассылаются данные*/
    private DatagramSocket socket;
    /** Порт, на котором работает данный сервер*/
    private int port;
    /** Поток, прослушивающий порт*/
    private SPortListener spl;
    /** Массив адресов клиентов и соответсвующих им никнеймов*/
    private Map<InetAddress, String> clients = new HashMap<>();

    /**
     * Стандартный конструктор
     */
    public Server(){
        this.port = 8888;
    }

    /**
     * Проводит все нужные приготовления и запускает сервер
     */
    public void startServer() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {}
        spl = new SPortListener(this, port);
        spl.start();
    }

    /**
     * Получает на вход пакет данных, читает сообщение и мотрит, что с ним делать
     * Либо добавить пользователя, либо удалить, либо отправить кому то линое сообщение, либо разослать сообщение всем в чате
     * @param packet пакет данных
     */
    public void readMessage(DatagramPacket packet) {
        byte[] data = packet.getData();
        String message = null;
        try {
            message = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if(message.matches("/firstmessage\\$.*\\$.*")){
            if(!isAlreadyConnected(packet)) {
                String name = message.split("\\$")[1];
                addClient(packet, name);
                broadcastMessage("new user " + name, "Server");
            }else{
                unicast("Someone tried to login with ur name", "Server", message.split("\\$")[1]);
            }
            return;
        }
        if(message.matches("\\$left.*")){
            broadcastMessage(clients.get(packet.getAddress()) + " left", "Server");
            removeClient(packet);
            return;
        }
        if(message.matches("/unicast\\s\\$.*\\$.*")){
            unicast(packet, clients.get(packet.getAddress()), message.split("\\$")[1]);
            return;
        }
        broadcastMessage(packet, clients.get(packet.getAddress()));
    }

    /**
     * Метод отправляет сообщение от одного пользователя лично другому
     * @param message пакет данных, пришедший от клиента
     * @param author имя автора
     * @param username имя адресата
     */
    public void unicast(DatagramPacket message, String author, String username) {
        try {

            byte[] data = Arrays.copyOfRange(message.getData(), 0, message.getLength());
            String newMessage = new String(data, "UTF-8");
            unicast(newMessage.split("\\$")[2], author, username);
        } catch (IOException e) {}
    }

    /**
     * Перегруженная версия предыдущего метода, которая делает то же самое
     * @param message сообщение, котрое нужно отправить лично адресату
     * @param author автор сообщения
     * @param username адресат
     */
    public void unicast(String message, String author, String username) {
        try {
            String fullMessage = author + "(unicast) :: " + message;
            for (Map.Entry<InetAddress, String> tmp : clients.entrySet()) {
                if(tmp.getValue().equals(username)) {
                    DatagramPacket packet = new DatagramPacket(fullMessage.getBytes(), fullMessage.length(), tmp.getKey(), port + 1);

                        socket.send(packet);

                }
            }
        } catch (IOException e) {}
    }

    /**
     * Рассылает сообщение всем пользователям, подключенным к серверу
     * @param message пакеты данных, пришедший о тклиента
     * @param username имя автора
     */
    public void broadcastMessage(DatagramPacket message, String username) {
        try {
            byte[] data = Arrays.copyOfRange(message.getData(), 0, message.getLength());
            String fullMessage = (new String(data, "UTF-8"));
            broadcastMessage(fullMessage, username);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Перегруженная версия прошлого метода, делает то же самое
     * @param message сообщение, которое нужно разослать
     * @param username автор сообщения
     */
    public void broadcastMessage(String message, String username){
        try {
            String fullMessage = username + " :: " + message;
            for(InetAddress tmp : clients.keySet()){
                DatagramPacket packet = new DatagramPacket(fullMessage.getBytes(), fullMessage.length(), tmp, port + 1);
                socket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавляет нового ползователя в чат
     * @param packet пакет данных, в котором содержится информация о пользоватеел
     * @param username имя пользователя
     */
    private void addClient(DatagramPacket packet, String username) {
        clients.put(packet.getAddress(), username);
    }

    /**
     * Удаляет данного пользователя
     * @param packet пакет данных о данном пользователе
     */
    private void removeClient(DatagramPacket packet) {
        clients.remove(packet.getAddress());
    }

    /**
     * Проверка, содержится ли пользователь с данным адресом среди тех, кто уже подключился
     * @param packet пакет данных с информацией о данном пользователе
     * @return true или false
     */
    private boolean isAlreadyConnected(DatagramPacket packet) {
        for (InetAddress tmp : clients.keySet()){
            if(tmp.equals(packet.getAddress())){
                return true;
            }
        }
        return false;
    }

    /**
     * Начинает процесс завершения работы сервера
     */
    public void stopServer() {
        spl.stopListen();
        try {
            spl.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        socket.close();
    }
}
