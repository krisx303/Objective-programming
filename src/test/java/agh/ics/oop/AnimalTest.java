package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void hasDirection(){
        Animal animal = new Animal();
        assertEquals(animal.getDirection(), MapDirection.NORTH);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        assertEquals(animal.getDirection(), MapDirection.SOUTH);
        animal.move(MoveDirection.RIGHT);
        assertNotEquals(animal.getDirection(), MapDirection.SOUTH);
        assertEquals(animal.getDirection(), MapDirection.WEST);
    }

    @Test
    void isAt() {
        Animal animal = new Animal();
        assertTrue(animal.isAt(new Vector2d(2, 2)));
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2, 3)));
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(3, 3)));
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(1, 4)));
    }

    @Test
    void moveIfInArea() {
        Animal animal = new Animal();
        for (int i = 0; i < 4; i++) {
            animal.move(MoveDirection.FORWARD);
        }
        assertTrue(animal.isAt(new Vector2d(2, 4)));
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(2, 4)));
        Animal animal1 = new Animal();
        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        animal1.move(MoveDirection.BACKWARD);
        assertFalse(animal1.isAt(new Vector2d(-1, 2)));
        assertTrue(animal1.isAt(new Vector2d(0, 2)));
    }
}