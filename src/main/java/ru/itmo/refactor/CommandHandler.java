package ru.itmo.refactor;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.itmo.refactor.command.*;
import ru.itmo.refactor.model.MusicalComposition;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

@AllArgsConstructor
public class CommandHandler {
    private Reader reader;
    private Writer writer;
    private Collection<MusicalComposition> compositions;
    private boolean isRunning;

    @SneakyThrows
    public void handleCommands() {
        BufferedReader reader = new BufferedReader(this.reader);
        new HelpCommand(writer).execute();
        while (isRunning) {
            writer.write("Input the command:\n");
            writer.flush();
            String cmd = reader.readLine().toLowerCase();
            Command command = getCmdFromStr(cmd);
            command.execute();
            writer.write("----\n");
            writer.flush();
        }
    }

    private Command getCmdFromStr(String cmd) {
        Command command;
        switch (cmd) {
            case "list":
                command = new ListCommand(writer, compositions);
                break;
            case "add":
                command = new AddCommand(reader, writer, compositions);
                break;
            case "del":
                command = new DelCommand(reader, writer, compositions);
                break;
            case "search":
                command = new SearchCommand(reader, writer, compositions);
                break;
            case "quit":
                command = new QuitCommand(writer, () -> isRunning = false);
                break;
            case "help":
                command = new HelpCommand(writer);
                break;
            default:
                command = new UnknownCommand(writer);
                break;
        }
        return command;
    }
}
