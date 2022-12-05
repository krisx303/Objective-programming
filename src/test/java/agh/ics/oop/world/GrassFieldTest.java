package agh.ics.oop.world;

import agh.ics.oop.*;
import agh.ics.oop.engine.SimulationEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void testAnimalMoving(){
        String[] args = new String[]{"f", "r", "forward", "b", "b", "b"};
        MoveDirection[] directions = OptionParser.parse(args);
        GrassField map = new GrassField(2);
        Vector2d[] positions = {new Vector2d(1, 1), new Vector2d(2, 2)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();

        assertTrue(map.isOccupied(new Vector2d(0, 2)));
        assertTrue(map.isOccupied(new Vector2d(1, 3)));
        assertInstanceOf(Animal.class, map.objectAt(new Vector2d(0, 2)));
        assertInstanceOf(Animal.class, map.objectAt(new Vector2d(1, 3)));
    }

    @Test
    void testAnimalUniquePosition(){
        String[] args = new String[]{"f", "l", "r", "f", "f", "b", "r", "f", "r", "f", "b"};
        MoveDirection[] directions = OptionParser.parse(args);
        GrassField map = new GrassField(3);
        Vector2d[] positions = {new Vector2d(1, 1), new Vector2d(2, 2), new Vector2d(1, 1)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        assertThrows(IllegalArgumentException.class, engine::run);
    }

}