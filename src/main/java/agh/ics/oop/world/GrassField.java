package agh.ics.oop.world;

import agh.ics.oop.Animal;
import agh.ics.oop.Grass;
import agh.ics.oop.MapBoundary;
import agh.ics.oop.Vector2d;

public class GrassField extends AbstractWorldMap {
    private final int startN;
    private Vector2d grassBarrier;

    private final MapBoundary mapBoundary;

    public GrassField(int n) {
        startN = n;
        mapBoundary = new MapBoundary();
    }

    @Override
    public void init() {
        int roundedSqrt = (int) Math.round(Math.sqrt(startN * 10));
        grassBarrier = new Vector2d(roundedSqrt, roundedSqrt);
        for (int i = 0; i < startN; i++) {
            generateGrass();
        }
    }

    @Override
    public Vector2d getLowerLeft() {
        return mapBoundary.getLowerLeft();
    }

    @Override
    public Vector2d getUpperRight() {
        return mapBoundary.getUpperRight();
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException {
        super.place(animal);
        animal.addObserver(mapBoundary);
        mapBoundary.addElement(animal.getPosition());
        return true;
    }

    private void generateGrass() {
        int maxCount = (grassBarrier.x - getLowerLeft().x + 1) * (grassBarrier.y - getLowerLeft().y + 1);
        if (mapElements.size() == maxCount) return; // No place on the map to generate grass
        Vector2d vector2d = Generator.generateRandomVector(getLowerLeft(), grassBarrier);
        while (isOccupied(vector2d)) {
            vector2d = Generator.generateRandomVector(getLowerLeft(), grassBarrier);
        }
        Grass grass = new Grass(vector2d);
        mapElements.put(vector2d, grass);
        mapBoundary.addElement(grass.getPosition());
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return getAnimals().stream().noneMatch(animal -> animal.isAt(position));
    }

    public void replaceGrass(Grass grass) {
        mapElements.remove(grass);
        mapBoundary.removeElement(grass.getPosition());
        int maxCount = (getUpperRight().x - getLowerLeft().x + 1) * (getUpperRight().y - getLowerLeft().y + 1);
        if (mapElements.size() == maxCount) return; // No place on the map to generate grass
        Vector2d vector2d = Generator.generateRandomVector(getLowerLeft(), getUpperRight());
        while (isOccupied(vector2d) || grass.getPosition().equals(vector2d)) {
            vector2d = Generator.generateRandomVector(getLowerLeft(), getUpperRight());
        }
        Grass element = new Grass(vector2d);
        mapElements.put(vector2d, element);
        mapBoundary.addElement(element.getPosition());
    }
}
