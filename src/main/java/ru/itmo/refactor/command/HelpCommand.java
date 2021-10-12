package ru.itmo.refactor.command;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.Writer;

@AllArgsConstructor
public class HelpCommand implements Command {

    private final Writer writer;

    @SneakyThrows
    @Override
    public void execute() {
        writer.write("List of commands:\n");
        writer.write("* \"list\" - display all items of catalog\n");
        writer.write("* \"search\" - find items in catalog\n");
        writer.write("* \"add\" - add a new item\n");
        writer.write("* \"del\" - remove an existing item\n");
        writer.write("* \"help\" - print information about available commands\n");
        writer.write("* \"quit\" - disconnect from the server and exit the program\n");
        writer.flush();
    }
}
