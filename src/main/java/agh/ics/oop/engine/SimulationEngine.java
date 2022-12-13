package agh.ics.oop.engine;

import agh.ics.oop.Animal;
import agh.ics.oop.IFrameChangeObserver;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.Vector2d;
import agh.ics.oop.world.IWorldMap;

import java.util.List;

public class SimulationEngine implements IEngine, Runnable {

    private MoveDirection[] directions;

    private final IWorldMap map;

    private final Vector2d[] positions;

    private IFrameChangeObserver observer;

    private int moveDelay = 0;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions) {
        this.directions = directions;
        this.map = map;
        this.positions = positions;
    }

    /** Initializing Animals from given Vector2D array and output. For debug use ConsoleOutput class, for release - FrameOutput class */
    public void init() throws IllegalArgumentException {
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            map.place(animal);
        }
        map.init();
    }

    @Override
    public void run() throws IllegalArgumentException {
        List<Animal> animals = map.getAnimals();
        int size = animals.size();
        for (int i = 0; i < directions.length; i++) {
            animals.get(i%size).move(directions[i]);
            System.out.println(map);
            if(observer != null)
                observer.updateFrame();
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setObserver(IFrameChangeObserver observer) {
        this.observer = observer;
    }

    public void setMoveDelay(int moveDelay) {
        this.moveDelay = moveDelay;
    }

    public void setDirections(MoveDirection[] directions) {
        this.directions = directions;
    }
}
