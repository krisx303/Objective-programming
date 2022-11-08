package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        MoveDirection[] moves = OptionParser.parse(args);
        Arrays.stream(moves).forEach(animal::move);
        System.out.println(animal);
    }
}
