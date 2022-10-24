package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void precedes() {
        Vector2d vec1 = new Vector2d(-3, 2);
        Vector2d vec2 = new Vector2d(-6, 2);
        Vector2d vec3 = new Vector2d(-3, 5);
        assertTrue(vec2.precedes(vec1));
        assertFalse(vec3.precedes(vec2));
        assertTrue(vec2.precedes(vec3));
    }


    @Test
    void follows() {
        Vector2d vec1 = new Vector2d(1, 2);
        Vector2d vec2 = new Vector2d(-2, 10);
        Vector2d vec3 = new Vector2d(5, 2);
        assertTrue(vec3.follows(vec1));
        assertFalse(vec2.follows(vec1));
        assertFalse(vec1.follows(vec2));
    }

    @Test
    void add() {
        Vector2d vec1 = new Vector2d(1, 0);
        Vector2d vec2 = new Vector2d(0, -1);
        assertEquals(vec1.add(vec2), new Vector2d(1, -1));
        assertEquals(vec1.add(vec2), vec2.add(vec1));
    }

    @Test
    void subtract() {
        Vector2d vec1 = new Vector2d(3, 4);
        Vector2d vec2 = new Vector2d(-1, 2);
        assertEquals(vec1.subtract(vec2), new Vector2d(4, 2));
    }

    @Test
    void upperRight() {
        Vector2d vec1 = new Vector2d(1, 2);
        Vector2d vec2 = new Vector2d(-2, 10);
        Vector2d vec3 = new Vector2d(5, 2);
        assertEquals(vec1.upperRight(vec2), new Vector2d(1, 10));
        assertNotEquals(vec3.upperRight(vec1), new Vector2d(5, 1));
        assertEquals(vec2.upperRight(vec3), new Vector2d(5, 10));
    }

    @Test
    void lowerLeft() {
        Vector2d vec1 = new Vector2d(10, -2);
        Vector2d vec2 = new Vector2d(-25, 130);
        Vector2d vec3 = new Vector2d(523, 24);
        assertEquals(vec1.lowerLeft(vec2), new Vector2d(-25, -2));
        assertNotEquals(vec3.lowerLeft(vec1), new Vector2d(523, -2));
        assertEquals(vec2.lowerLeft(vec3), new Vector2d(-25, 24));
    }

    @Test
    void opposite() {
        Vector2d vec1 = new Vector2d(5, 4);
        Vector2d vec2 = new Vector2d(-5, -4);
        assertEquals(vec1.opposite(), vec2);
        assertNotEquals(vec2.opposite(), new Vector2d(-5, 4));
    }

    @Test
    void testEquals() {
        Vector2d vec1 = new Vector2d(1, 0);
        Vector2d vec2 = new Vector2d(2, 1);
        assertNotEquals(vec1, vec2);
        assertEquals(vec1, vec1);
        assertEquals(vec2, vec2);
        Vector2d vec3 = new Vector2d(2, 1);
        assertEquals(vec2, vec3);
    }

    @Test
    void testToString() {
        assertEquals(new Vector2d(2, 1).toString(), "(2,1)");
        assertEquals(new Vector2d(-123, 321).toString(), "(-123,321)");
    }
}