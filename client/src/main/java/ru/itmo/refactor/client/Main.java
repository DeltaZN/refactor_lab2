package ru.itmo.refactor.client;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Client client = new Client("127.0.0.1", 18080);
        while (true) {
            String str = reader.readLine();
            client.sendMessage(str);
        }
    }
}
