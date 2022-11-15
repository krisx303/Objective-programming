package agh.ics.oop.out;

import agh.ics.oop.world.IWorldMap;

public class ConsoleOutput implements IOutput {

    private IWorldMap map;
    @Override
    public void update() {
        System.out.printf(map.toString());
    }

    @Override
    public void init(IWorldMap map) {
        this.map = map;
        System.out.println("Console output initialized successfully ;)");
    }
}
