package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
class OptionParserTest {

    @Test
    void parse() {
        assertThrows(IllegalArgumentException.class, () -> OptionParser.parse(
                new String[]{"l", "b", "ble", "", "l", "f", "r", "r"}
        ));
    }

    @Test
    void illegalInputArgument(){
        String[] args = new String[]{"f", "backward", "g"};
        assertThrows(IllegalArgumentException.class, () -> OptionParser.parse(args));
        String[] args2 = new String[]{"f", "b", "f", "r", "l", "right", "left", "forward", "backward", "f"};
        assertDoesNotThrow(() -> OptionParser.parse(args2));
    }
}