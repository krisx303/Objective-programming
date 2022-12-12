package agh.ics.oop;

import java.util.SortedMap;
import java.util.TreeMap;

public class MapBoundary implements IPositionChangeObserver {
    private final SortedMap<Integer, Integer> xMap, yMap;

    public MapBoundary() {
        xMap = new TreeMap<>();
        yMap = new TreeMap<>();
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        if (oldPosition.x != newPosition.x)
            removeByKey(xMap, oldPosition.x);
        if (oldPosition.y != newPosition.y)
            removeByKey(yMap, oldPosition.y);
        addByKey(xMap, newPosition.x);
        addByKey(yMap, newPosition.y);
    }

    private void removeByKey(SortedMap<Integer, Integer> map, int key) {
        if (map.get(key) == 1)
            map.remove(key);
        else
            map.put(key, map.get(key));
    }

    private void addByKey(SortedMap<Integer, Integer> map, int key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }


    public Vector2d getLowerLeft() {
        if (xMap.isEmpty()) return new Vector2d(0, 0);
        return new Vector2d(xMap.firstKey(), yMap.firstKey());
    }

    public Vector2d getUpperRight() {
        if (xMap.isEmpty()) return new Vector2d(0, 0);
        return new Vector2d(xMap.lastKey(), yMap.lastKey());
    }

    public void addElement(Vector2d vector) {
        addByKey(xMap, vector.x);
        addByKey(yMap, vector.y);
    }

    public void removeElement(Vector2d vector) {
        removeByKey(xMap, vector.x);
        removeByKey(yMap, vector.y);
    }
}
