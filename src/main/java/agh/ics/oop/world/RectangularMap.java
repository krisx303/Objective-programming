package agh.ics.oop.world;

import agh.ics.oop.Vector2d;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int width, int height) {
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean s = super.canMoveTo(position);
        return s && position.follows(lowerLeft) && position.precedes(upperRight);
    }

    @Override
    public void updateVectors() {}
}
