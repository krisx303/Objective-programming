package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        Direction[] directions = Arrays.stream(args).map(Direction::getDirectionByLabel).toArray(Direction[]::new);
        run(directions);
        System.out.println("system zakończył działanie");
    }

    private static void run(Direction[] directions){
        Arrays.stream(directions).forEach(direction -> System.out.println(direction.getOutput()));
    }
}
