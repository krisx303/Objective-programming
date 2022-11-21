package agh.ics.oop.world;

import agh.ics.oop.Vector2d;

import java.util.Random;

public class Generator {
    public static Vector2d generateRandomVector(Vector2d lowerLeft, Vector2d upperRight){
        Random random = new Random();
        int x = random.nextInt(Math.abs(upperRight.x - lowerLeft.x) + lowerLeft.x);
        int y = random.nextInt(Math.abs(upperRight.y - lowerLeft.y) + lowerLeft.y);
        return new Vector2d(x, y);
    }
}
