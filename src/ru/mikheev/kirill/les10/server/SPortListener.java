package ru.mikheev.kirill.les10.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public class SPortListener extends Thread {

    Server server;
    boolean isRunning;
    DatagramPacket remoteData;
    byte[] buffer;
    int port;

    public SPortListener(Server server, int port) {
        this.server = server;
        this.port = port;
        buffer = new byte[65000];
        remoteData = new DatagramPacket(buffer, buffer.length);
    }

    @Override
    public synchronized void start() {
        isRunning = true;
        super.start();
    }

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

    public void stopListen(){
        isRunning = false;
    }
}
