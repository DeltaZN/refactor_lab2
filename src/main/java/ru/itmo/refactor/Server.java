package ru.itmo.refactor;

import lombok.SneakyThrows;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket serverSocket;
    private final Context context;
    private final ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private boolean isRunning = true;

    public Server(Context context) {
        this.context = context;
    }

    @SneakyThrows
    public void start(int port) {
        serverSocket = new ServerSocket(port);
        System.out.println("Server is running");
        while (isRunning) {
            serveClient(serverSocket.accept());
            System.out.println("Connection accepted");
        }
    }

    private void serveClient(Socket clientSocket) {
        ClientHandler clientHandler = new ClientHandler(clientSocket, context.getCompositions());
        clientHandlers.add(clientHandler);
        new Thread(clientHandler).start();
    }

    @SneakyThrows
    public void stop() {
        isRunning = false;
        clientHandlers.forEach(ClientHandler::stop);
        serverSocket.close();
    }
}
