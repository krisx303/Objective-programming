package agh.ics.oop.world;

import agh.ics.oop.Animal;
import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.Vector2d;
import agh.ics.oop.out.MapVisualizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected final Map<Vector2d, IMapElement> mapElements;
    protected Vector2d lowerLeft, upperRight;
    private final MapVisualizer visualizer;

    public AbstractWorldMap() {
        this.visualizer = new MapVisualizer(this);
        this.mapElements = new HashMap<>();
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position == null) {
            return false;
        }
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (mapElements.get(animal.getPosition()) != null || isOccupied(animal.getPosition())) {
            return false;
        }
        mapElements.put(animal.getPosition(), animal);
        animal.addObserver(this);
        return true;
    }

    public abstract void updateVectors();

    @Override
    public void init() {
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return mapElements.get(position) != null;
        //return mapElements.stream().anyMatch(element -> element.isAt(position));
    }

    @Override
    public Object objectAt(Vector2d position) {
        return mapElements.get(position);
//        return mapElements.stream().filter(element -> element.isAt(position)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        updateVectors();
        return visualizer.draw(lowerLeft, upperRight);
    }

    @Override
    public List<Animal> getAnimals() {
        return mapElements.values().stream().filter(iMapElement -> iMapElement instanceof Animal).map(iMapElement -> (Animal) iMapElement).toList();
    }

    @Override
    public List<IMapElement> getMapElements() {
        return mapElements.values().stream().toList();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement element = mapElements.get(oldPosition);
        mapElements.remove(oldPosition);
        mapElements.put(newPosition, element);
    }
}
