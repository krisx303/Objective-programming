package agh.ics.oop;

public class Animal {

    /**The direction in which the animal is currently turned */
    private MapDirection direction;
    /** Position of the animal as (x, y) vector*/
    private Vector2d position;

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "direction=" + direction +
                ", position=" + position +
                '}';
    }

    /** Check if animal is at position */
    boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    /** Move the animal forward/backward if possible or changes its direction*/
    void move(MoveDirection direction){
        switch (direction){
            case FORWARD -> moveIfInArea(this.direction.toUnitVector());
            case BACKWARD -> moveIfInArea(this.direction.toUnitVector().opposite());
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
        }

    }

    /** Move the Animal to new position (CurrentPosition + movement) only if the new position still belongs to Area
    * */

    private void moveIfInArea(Vector2d movement){
        // Const vectors limiting the field in which the animal can move
        // Probably should be final variable of Animal
        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(4, 4);
        Vector2d tempPos = new Vector2d(this.position.x, this.position.y);
        tempPos = tempPos.add(movement);
        if(tempPos.isInArea(lowerLeft, upperRight)){
            this.position = this.position.add(movement);
        }
    }

    public MapDirection getDirection() {
        return direction;
    }

}
