package agh.ics.oop.world;

import agh.ics.oop.Animal;
import agh.ics.oop.Vector2d;
import agh.ics.oop.out.MapVisualizer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{

    protected final List<IMapElement> mapElements;
    protected Vector2d lowerLeft, upperRight;
    private final MapVisualizer visualizer;

    public AbstractWorldMap() {
        this.visualizer = new MapVisualizer(this);
        this.mapElements = new ArrayList<>();
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if(position == null){
            return false;
        }
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(mapElements.contains(animal) || isOccupied(animal.getPosition())){
            return false;
        }
        mapElements.add(animal);
        return true;
    }

    public abstract void updateVectors();

    @Override
    public void init() {}

    @Override
    public boolean isOccupied(Vector2d position) {
        return mapElements.stream().anyMatch(element -> element.isAt(position));
    }

    @Override
    public Object objectAt(Vector2d position) {
        return mapElements.stream().filter(element -> element.isAt(position)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        updateVectors();
        return visualizer.draw(lowerLeft, upperRight);
    }

    @Override
    public List<Animal> getAnimals() {
        return mapElements.stream().filter(element -> element instanceof Animal).
                map(element -> (Animal)element).toList();
    }

    @Override
    public List<IMapElement> getMapElements() {
        return mapElements;
    }
}
