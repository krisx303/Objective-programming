package agh.ics.oop;

import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.world.IWorldMap;
import agh.ics.oop.world.RectangularMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    @Test
    void testAnimalMoving(){
        String[] args = new String[]{"f", "r", "forward", "b", "b", "b"};
        MoveDirection[] directions = OptionParser.parse(args);
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d[] positions = {new Vector2d(1, 1), new Vector2d(2, 2)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();

        assertTrue(map.isOccupied(new Vector2d(0, 2)));
        assertTrue(map.isOccupied(new Vector2d(1, 3)));
        assertFalse(map.isOccupied(new Vector2d(1, 2)));
        assertFalse(map.isOccupied(new Vector2d(3, 3)));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
    }

    @Test
    void testAnimalUniquePosition(){
        String[] args = new String[]{"f", "l", "r", "f", "f", "b", "r", "f", "r", "f", "b"};
        MoveDirection[] directions = OptionParser.parse(args);
        RectangularMap map = new RectangularMap(5, 5);
        Vector2d[] positions = {new Vector2d(1, 1), new Vector2d(2, 2), new Vector2d(1, 1)};
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();
        assertEquals(2, map.getAnimals().size());

        assertTrue(map.isOccupied(new Vector2d(1, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertFalse(map.isOccupied(new Vector2d(0, 2)));
        assertFalse(map.isOccupied(new Vector2d(3, 3)));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
    }

    @Test
    void testAnimalStaysInArea(){
        String[] args = new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();

        assertTrue(map.isOccupied(new Vector2d(2, 0)));
        assertTrue(map.isOccupied(new Vector2d(3, 5)));
        assertFalse(map.isOccupied(new Vector2d(2, -1)));
        assertFalse(map.isOccupied(new Vector2d(3, 6)));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
    }
}
