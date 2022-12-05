package agh.ics.oop;

import agh.ics.oop.engine.IEngine;
import agh.ics.oop.engine.SimulationEngine;
import agh.ics.oop.world.GrassField;
import agh.ics.oop.world.IWorldMap;
import agh.ics.oop.world.RectangularMap;

public class World {
    public static void main(String[] args) {
        try {
            MoveDirection[] directions = OptionParser.parse(args);
//            IWorldMap map = new RectangularMap(10, 5);
          IWorldMap map = new GrassField(6);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }
}
