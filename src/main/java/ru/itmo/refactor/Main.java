package ru.itmo.refactor;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        Server server = new Server(context);
        server.start(18080);
        server.stop();
    }
}
