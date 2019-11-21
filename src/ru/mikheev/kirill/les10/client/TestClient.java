package ru.mikheev.kirill.les10.client;

/**
 * Класс для тестового запуска Клиента
 * @author Kirill Mikheev
 * @version 1.0
 */

public class TestClient {
    public static void main(String[] args) {
        Client client = new Client( "127.0.0.1");
        client.startClient();
    }
}
