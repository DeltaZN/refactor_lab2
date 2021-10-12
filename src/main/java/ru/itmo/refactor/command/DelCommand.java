package ru.itmo.refactor.command;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.itmo.refactor.model.MusicalComposition;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;

@AllArgsConstructor
public class DelCommand implements Command {

    private final Reader reader;
    private final Writer writer;
    private final Collection<MusicalComposition> musicalCompositions;

    @Override
    @SneakyThrows
    public void execute() {
        BufferedReader br = new BufferedReader(reader);
        writer.write("Input author's name:\n");
        writer.flush();
        String author = br.readLine();
        writer.write("Input the composition's name:\n");
        writer.flush();
        String name = br.readLine();
        MusicalComposition composition = new MusicalComposition(name, author);
        if (musicalCompositions.removeIf(c -> c.equals(composition))) {
            writer.write(String.format("Track \"%s\" deleted.\n", composition));
        } else {
            writer.write(String.format("Track \"%s\" was not found.\n", composition));
        }
    }
}
