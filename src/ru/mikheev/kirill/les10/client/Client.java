package ru.mikheev.kirill.les10.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Client {

    private int port;
    private InetAddress address;
    private DatagramSocket socket;

    public Client(String host) {
        this.port = 8888;
        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.out.println("WrongHost");
        }
    }

    public void setHost(String host) {
        try {
            address = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            System.out.println("WrongHost");
        }
    }

    public void startClient() {
        CPortListener cpl = new CPortListener(this, port + 1);
        cpl.start();
        readConsole();
        cpl.stopListen();
    }

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

    public void readConsole() {
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

    public void sendMessage(String message) {
        try {
            DatagramPacket firstPacket = new DatagramPacket(message.getBytes(), message.length(), address, port);
            socket.send(firstPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(DatagramPacket packet) {
        try {
            String message = new String(packet.getData(), "UTF-8");
            System.out.println(message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
