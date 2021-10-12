package ru.itmo.refactor.client;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private final Socket clientSocket;
    private final PrintWriter out;
    private final BufferedReader in;

    @SneakyThrows
    public Client(String ip, int port) {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        new Thread(() -> {
            try {
                while (true) {
                    String str = in.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    @SneakyThrows
    public void stopConnection() {
        in.close();
        out.close();
        clientSocket.close();
    }
}
