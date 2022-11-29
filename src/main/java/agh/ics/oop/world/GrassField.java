package agh.ics.oop.world;

import agh.ics.oop.Grass;
import agh.ics.oop.Vector2d;

public class GrassField extends AbstractWorldMap {
    private final int startN;
    private Vector2d grassBarrier;
    public GrassField(int n){
        super();
        startN = n;
        lowerLeft = new Vector2d(0, 0);
        upperRight = new Vector2d(0, 0);
    }
    @Override
    public void init(){
        int roundedSqrt = (int) Math.round(Math.sqrt(startN*10));
        grassBarrier = new Vector2d(roundedSqrt, roundedSqrt);
        for (int i = 0; i < startN; i++) {
            generateGrass();
        }
        updateVectors();
    }

    private void generateGrass() {
        int maxCount = (grassBarrier.x - lowerLeft.x + 1) * (grassBarrier.y - lowerLeft.y + 1);
        if(mapElements.size() == maxCount) return; // No place on the map to generate grass
        Vector2d vector2d = Generator.generateRandomVector(lowerLeft, grassBarrier);
        while (isOccupied(vector2d)){
            vector2d = Generator.generateRandomVector(lowerLeft, grassBarrier);
        }
        mapElements.put(vector2d, new Grass(vector2d));
    }

    @Override
    public void updateVectors() {
        int[] values = {2147483647, 0, 2147483647, 0};
        mapElements.keySet().forEach(vector2d -> {
            values[0] = Math.min(values[0], vector2d.x);
            values[2] = Math.min(values[2], vector2d.y);
            values[1] = Math.max(values[1], vector2d.x);
            values[3] = Math.max(values[3], vector2d.y);
        });
        lowerLeft = new Vector2d(values[0]-1, values[2]-1);
        upperRight = new Vector2d(values[1]+1, values[3]+1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return getAnimals().stream().noneMatch(animal -> animal.isAt(position));
    }

    public void replaceGrass(Grass grass) {
        mapElements.remove(grass);
        int maxCount = (upperRight.x - lowerLeft.x + 1) * (upperRight.y - lowerLeft.y + 1);
        if(mapElements.size() == maxCount) return; // No place on the map to generate grass
        Vector2d vector2d = Generator.generateRandomVector(lowerLeft, upperRight);
        while (isOccupied(vector2d) || grass.getPosition().equals(vector2d)){
            vector2d = Generator.generateRandomVector(lowerLeft, upperRight);
        }
        mapElements.put(vector2d, new Grass(vector2d));
    }
}
