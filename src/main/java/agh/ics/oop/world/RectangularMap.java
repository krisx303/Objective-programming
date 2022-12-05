package agh.ics.oop.world;

import agh.ics.oop.Vector2d;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d lowerLeft, upperRight;
    public RectangularMap(int width, int height) {
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width, height);
    }

    @Override
    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    @Override
    public Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean s = super.canMoveTo(position);
        return s && position.follows(getLowerLeft()) && position.precedes(getUpperRight());
    }
}
