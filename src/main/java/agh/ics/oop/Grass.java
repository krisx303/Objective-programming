package agh.ics.oop;

import agh.ics.oop.world.IMapElement;

public class Grass implements IMapElement {
    private final Vector2d position;
    public Grass(Vector2d position){
        this.position = position;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public boolean isAt(Vector2d vector2d) {
        return position.equals(vector2d);
    }

    @Override
    public String getResourcePath() {
        return "grass.png";
    }

    @Override
    public String getLabel() {
        return "Trawa";
    }

    @Override
    public String toString() {
        return "*";
    }
}
