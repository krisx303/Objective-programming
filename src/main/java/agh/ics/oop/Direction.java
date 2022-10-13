package agh.ics.oop;

import java.util.Arrays;

public enum Direction {
    FORWARD("f", "Zwierzak idzie do przodu"),
    BACKWARD("b", "Zwierzak idzie do tyłu"),
    RIGHT("r", "Zwierzak skręca w prawo"),
    LEFT("l", "Zwierzak skręca w lewo");

    private final String label;
    private final String output;

    Direction(String label, String output) {
        this.label = label;
        this.output = output;
    }

    public static Direction getDirectionByLabel(String str){
        return Arrays.stream(Direction.values()).
                filter(direction -> direction.label.equals(str)).
                findFirst().orElse(FORWARD);
    }

    public String getOutput() {
        return output;
    }
}
