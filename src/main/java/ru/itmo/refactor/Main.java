package ru.itmo.refactor;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Main {
    public static void main(String[] args) {
        Context context = new Context();
        Reader reader = new InputStreamReader(System.in);
        Writer writer = new OutputStreamWriter(System.out);

        CommandHandler commandHandler = new CommandHandler(reader, writer, context);
        commandHandler.handleCommands();
    }
}
