package ru.itmo.refactor;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.itmo.refactor.command.*;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.Writer;

@AllArgsConstructor
public class CommandHandler {
    private Reader reader;
    private Writer writer;
    private Context context;

    @SneakyThrows
    public void handleCommands() {
        BufferedReader reader = new BufferedReader(this.reader);
        while (context.isRunning()) {
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
                command = new ListCommand(writer, context.getCompositions());
                break;
            case "add":
                command = new AddCommand(reader, writer, context.getCompositions());
                break;
            case "del":
                command = new DelCommand(reader, writer, context.getCompositions());
                break;
            case "search":
                command = new SearchCommand(reader, writer, context.getCompositions());
                break;
            case "quit":
                command = new QuitCommand(writer, () -> context.setRunning(false));
                break;
            default:
                command = new UnknownCommand(writer);
                break;
        }
        return command;
    }
}
