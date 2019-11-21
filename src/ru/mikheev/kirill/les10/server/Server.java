package ru.mikheev.kirill.les10.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Server {

    private DatagramSocket socket;
    private int port;
    private SPortListener spl;

    private Map<InetAddress, String> clients = new HashMap<>();

    public Server(){
        this.port = 8888;
    }

    public void startServer() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {}
        spl = new SPortListener(this, port);
        spl.start();
    }

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
                broadcastMessage(("new user " + name).getBytes(), "Server");
            }else{
                unicast("Someone tried to login with ur name".getBytes(), "Server", message.split("\\$")[1]);
            }
            return;
        }
        if(message.matches("\\$left.*")){
            removeClient(packet);
        }
        if(message.matches("/unicast\\s\\$.*\\$.*")){
            unicast(message.split("\\$")[2].getBytes(), clients.get(packet.getAddress()), message.split("\\$")[1]);
            return;
        }
        broadcastMessage(message.getBytes(), clients.get(packet.getAddress()));
    }

    public void unicast(byte[] message, String author, String username) {
        String data;
        try {
            data = author + "(unicast) :: " + (new String(message, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            data = "Server :: bad message";
        }
        try {
            for (Map.Entry<InetAddress, String> tmp : clients.entrySet()) {
                if(tmp.getValue().equals(username)) {
                    DatagramPacket packet = new DatagramPacket(data.getBytes(), data.length(), tmp.getKey(), port + 1);
                        socket.send(packet);
                }
            }
        } catch (IOException e) {}
    }

    public void broadcastMessage(byte[] message, String username) {
        try {
            String data = username + " :: " + (new String(message, "UTF-8"));
            for(InetAddress tmp : clients.keySet()){
                DatagramPacket packet = new DatagramPacket(data.getBytes(), data.length(), tmp, port + 1);
                socket.send(packet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addClient(DatagramPacket packet, String username) {
        clients.put(packet.getAddress(), username);
    }

    private void removeClient(DatagramPacket packet) {
        clients.remove(packet.getAddress());
    }

    private boolean isAlreadyConnected(DatagramPacket packet) {
        for (InetAddress tmp : clients.keySet()){
            if(tmp.equals(packet.getAddress())){
                return true;
            }
        }
        return false;
    }

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
