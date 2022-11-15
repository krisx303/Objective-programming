package agh.ics.oop.world;

import agh.ics.oop.Animal;
import agh.ics.oop.out.MapVisualizer;
import agh.ics.oop.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {

    private final List<Animal> animals;
    private final MapVisualizer visualizer;
    private final Vector2d lowerLeft, upperRight;

    public RectangularMap(int width, int height) {
        this.animals = new ArrayList<>();
        this.visualizer = new MapVisualizer(this);
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = lowerLeft.add(new Vector2d(width, height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(position == null || !position.follows(lowerLeft) || !position.precedes(upperRight)){
            return false;
        }
        return !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(animals.contains(animal) || isOccupied(animal.getPosition())){
            return false;
        }
        animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.stream().anyMatch(animal -> animal.isAt(position));
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.stream().filter(animal -> animal.isAt(position)).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return visualizer.draw(lowerLeft, upperRight);
    }

    public Vector2d getSizeVector(){
        return upperRight;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
