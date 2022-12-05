package agh.ics.oop.world;

import agh.ics.oop.Animal;
import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.Vector2d;
import agh.ics.oop.out.MapVisualizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final Map<Vector2d, IMapElement> mapElements;
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
    public boolean place(Animal animal) throws IllegalArgumentException {
        if (mapElements.get(animal.getPosition()) != null || isOccupied(animal.getPosition())) {
            throw new IllegalArgumentException("Animal cannot be placed on map at " + animal.getPosition());
        }
        mapElements.put(animal.getPosition(), animal);
        animal.addObserver(this);
        return true;
    }

    @Override
    public void init() {}

    public abstract Vector2d getLowerLeft();

    public abstract Vector2d getUpperRight();

    @Override
    public boolean isOccupied(Vector2d position) {
        return mapElements.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    @Override
    public String toString() {
        return visualizer.draw(getLowerLeft(), getUpperRight());
    }

    @Override
    public List<Animal> getAnimals() {
        return mapElements.values().stream().filter(iMapElement -> iMapElement instanceof Animal).
                map(iMapElement -> (Animal) iMapElement).toList();
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
