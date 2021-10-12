package ru.itmo.refactor.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class MusicalComposition {
    private String name;
    private String author;

    @Override
    public String toString() {
        return String.format("%s - %s", author, name);
    }
}
