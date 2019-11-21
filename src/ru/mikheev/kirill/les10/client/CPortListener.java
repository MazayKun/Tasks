package ru.mikheev.kirill.les10.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * @author Kirill Mikheev
 * @version 1.0
 */

public class CPortListener extends Thread {

    private int port;
    private Client client;
    private boolean isRunning;
    private DatagramPacket remoteData;

    public CPortListener(Client client, int port) {
        this.client = client;
        this.port = port;
        byte[] buffer = new byte[65535];
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

    public void stopListen(){
        isRunning = false;
    }
}
