package agh.ics.oop;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {
        System.out.println("system wystartował");
        Direction[] directions = Arrays.stream(args).map(Direction::getDirectionByLabel).toArray(Direction[]::new);
//        int len = args.length;
//        Direction[] directions = new Direction[len];
//        for (int i = 0; i < args.length; i++) {
//            directions[i] = Direction.getDirectionByLabel(args[i]);
//        }
        run(directions);
        System.out.println("system zakończył działanie");
    }

    private static void run(Direction[] directions){
//        String joined = Arrays.stream(tab).map(Object::toString)
//                .collect(Collectors.joining(", "));
//        System.out.println(joined);
        Arrays.stream(directions).forEach(command -> {
            switch (command){
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tyłu");
                case RIGHT -> System.out.println("Zwierzak skręca w prawo");
                case LEFT -> System.out.println("Zwierzak skręca w lewo");
            }
        });
    }
}
