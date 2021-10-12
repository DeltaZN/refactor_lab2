package ru.itmo.refactor.command;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.itmo.refactor.model.MusicalComposition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SearchCommand implements Command {

    private final Reader reader;
    private final Writer writer;
    private final Collection<MusicalComposition> musicalCompositions;

    @Override
    @SneakyThrows
    public void execute() {
        BufferedReader br = new BufferedReader(reader);
        writer.write("Input the part of the name to find composition in the catalog:\n");
        writer.flush();
        String searchStr = br.readLine();
        List<String> composition = musicalCompositions.stream().map(MusicalComposition::toString).filter(c -> c.contains(searchStr)).collect(Collectors.toList());
        if (composition.size() == 0) {
            writer.write("No items were found by this criteria.\n");
        } else {
            composition.forEach(c -> {
                try {
                    writer.write(String.format("%s\n", c));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
