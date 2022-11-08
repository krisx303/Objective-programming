package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
class OptionParserTest {

    @Test
    void parse() {
        MoveDirection[] directions = new MoveDirection[]{
                MoveDirection.LEFT,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT
        };
        MoveDirection[] fromParser = OptionParser.parse(
                new String[]{"l", "b", "ble", "", "l", "f", "r", "r"}
        );
        assertTrue(Arrays.deepEquals(directions, fromParser));
    }
}