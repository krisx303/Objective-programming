package agh.ics.oop;

import agh.ics.oop.world.IWorldMap;

public class Animal {

    /**The direction in which the animal is currently turned */
    private MapDirection direction;


    /** Position of the animal as (x, y) vector*/
    private Vector2d position;

    /** Map where Animal is placed*/
    private IWorldMap map;


    public Animal(IWorldMap map, Vector2d initialPosition){
        this(map);
        this.position = initialPosition;
    }

    public Animal(IWorldMap map){
        this();
        this.map = map;
    }


    //TODO This constructor should be private
    public Animal() {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    @Override
    public String toString() {
        return direction.name().substring(0, 1);
    }

    /** Check if animal is at position */
    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    /** Move the animal forward/backward if possible or changes its direction*/
    public void move(MoveDirection direction){
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
        boolean canMove = map.canMoveTo(movement.add(position));
        if(canMove){
            position = position.add(movement);
        }
    }

    public MapDirection getDirection() {
        return direction;
    }

    public Vector2d getPosition() {
        return position;
    }
}
