package agh.ics.oop;

import java.util.Arrays;

public enum Direction {
    FORWARD("f"),
    BACKWARD("b"),
    RIGHT("r"),
    LEFT("l");

    private final String label;

    Direction(String label) {
        this.label = label;
    }

    public static Direction getDirectionByLabel(String str){
        return Arrays.stream(Direction.values()).
                filter(direction -> direction.label.equals(str)).
                findFirst().orElse(FORWARD);
    }
}
