package ru.itmo.refactor.command;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.itmo.refactor.model.MusicalComposition;

import java.io.Writer;
import java.util.Collection;

@AllArgsConstructor
public class ListCommand implements Command {

    private final Writer writer;
    private final Collection<MusicalComposition> musicalCompositions;

    @SneakyThrows
    @Override
    public void execute() {
        writer.write("All compositions in catalog:\n");
        for (MusicalComposition composition: musicalCompositions) {
            writer.write(String.format("%s\n", composition.toString()));
        }
        writer.flush();
    }
}
