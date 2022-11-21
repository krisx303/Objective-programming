package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;

public class OptionParser {
    public static MoveDirection[] parse(String[] instructions){
        return Arrays.stream(instructions).map(MoveDirection::getDirectionByLabel)
                .filter(Objects::nonNull).toArray(MoveDirection[]::new);
    }
}
