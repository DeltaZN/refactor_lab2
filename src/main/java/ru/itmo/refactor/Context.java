package ru.itmo.refactor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itmo.refactor.model.MusicalComposition;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
public class Context {
    private final ArrayList<MusicalComposition> compositions = new ArrayList<>();
    @Setter
    private boolean isRunning = true;
}
