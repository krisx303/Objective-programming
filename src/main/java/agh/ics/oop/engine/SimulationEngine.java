package agh.ics.oop.engine;

import agh.ics.oop.Animal;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.Vector2d;
import agh.ics.oop.out.ConsoleOutput;
import agh.ics.oop.out.FrameOutput;
import agh.ics.oop.out.IOutput;
import agh.ics.oop.world.IWorldMap;

import java.util.Arrays;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] directions;

    private final IWorldMap map;

    private final Vector2d[] positions;

    private IOutput output;
    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
    }

    /** Initializing Animals from given Vector2D array and output. For debug use ConsoleOutput class, for release - FrameOutput class */
    private void init(){
        Arrays.stream(positions).map(position -> new Animal(map, position)).forEach(map::place);
        output = new FrameOutput(); // or ConsoleOutput();
        map.init();
        output.init(map);
    }

    @Override
    public void run() {
        init();
        output.update();
        int size = map.getAnimals().size();
        for (int i = 0; i < directions.length; i++) {
            map.getAnimals().get(i%size).move(directions[i]);
            output.update();
        }
    }
}
