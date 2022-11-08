package agh.ics.oop;

import java.util.Arrays;

public enum MoveDirection {
    FORWARD("f"),
    BACKWARD("b"),
    RIGHT("r"),
    LEFT("l");

    private final String letter;

    MoveDirection(String letter) {
        this.letter = letter;
    }

    public static MoveDirection getDirectionByLabel(String str) {
        return Arrays.stream(MoveDirection.values()).
                filter(direction -> direction.letter.equals(str) || direction.name().toLowerCase().equals(str)).
                findFirst().orElse(null);
    }
}
