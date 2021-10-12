package ru.itmo.refactor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itmo.refactor.model.MusicalComposition;

import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@NoArgsConstructor
public class Context {
    private final CopyOnWriteArrayList<MusicalComposition> compositions = new CopyOnWriteArrayList<>();
    @Setter
    private boolean isRunning = true;
}
