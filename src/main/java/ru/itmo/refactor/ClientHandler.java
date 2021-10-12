package ru.itmo.refactor;

import lombok.SneakyThrows;
import ru.itmo.refactor.model.MusicalComposition;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.Collection;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private final Reader reader;
    private final Writer writer;
    private final Collection<MusicalComposition> compositions;

    @SneakyThrows
    public ClientHandler(Socket socket, Collection<MusicalComposition> compositions) {
        this.socket = socket;
        this.compositions = compositions;
        writer = new OutputStreamWriter(socket.getOutputStream());
        reader = new InputStreamReader(socket.getInputStream());
    }

    @Override
    public void run() {
        CommandHandler commandHandler = new CommandHandler(reader, writer, compositions, true);
        commandHandler.handleCommands();
        stop();
    }

    @SneakyThrows
    public void stop() {
        reader.close();
        writer.close();
        socket.close();
    }
}
