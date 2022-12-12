package agh.ics.oop.world;

import agh.ics.oop.Vector2d;

public interface IMapElement {
    Vector2d getPosition();
    boolean isAt(Vector2d vector2d);

    String getResourcePath();

    String getLabel();
}
