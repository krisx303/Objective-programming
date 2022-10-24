package agh.ics.oop;

public enum MapDirection {
    SOUTH("Południe", 0, -1),
    WEST("Zachód", -1, 0),
    NORTH("Północ", 0, 1),
    EAST("Wschód", 1, 0);

    private final String translation;
    private final int x, y;

    MapDirection(String translation, int x, int y) {
        this.translation = translation;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return this.translation;
    }

    MapDirection next(){
        return MapDirection.values()[(this.ordinal()+1) % MapDirection.values().length];
    }

    MapDirection previous(){
        return MapDirection.values()[(this.ordinal()+3)%MapDirection.values().length];
    }

    Vector2d toUnitVector(){
        return new Vector2d(this.x, this.y);
    }


}
