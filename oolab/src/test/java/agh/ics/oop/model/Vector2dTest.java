package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void precedes() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(4, 5);

        assertTrue(v1.precedes(v2));

        assertFalse(v2.precedes(v1));
    }

    @Test
    void follows() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(4, 5);

        assertFalse(v1.follows(v2));

        assertTrue(v2.follows(v1));
    }

    @Test
    void add() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(4, 5);

        Vector2d result = v1.add(v2);

        assertEquals(new Vector2d(6, 8), result);
    }

    @Test
    void subtract() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(4, 5);

        Vector2d result = v1.subtract(v2);

        assertEquals(new Vector2d(-2, -2), result);
    }

    @Test
    void upperRight() {
        Vector2d v1 = new Vector2d(6, 3);
        Vector2d v2 = new Vector2d(4, 5);

        Vector2d result = v1.upperRight(v2);

        assertEquals(new Vector2d(6, 5), result);
    }

    @Test
    void lowerLeft() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(1, 5);

        Vector2d result = v1.lowerLeft(v2);

        assertEquals(new Vector2d(1, 3), result);
    }

    @Test
    void opposite() {
        Vector2d v = new Vector2d(2, 3);

        Vector2d result = v.opposite();

        assertEquals(new Vector2d(-2, -3), result);
    }

    @Test
    void testToString() {
        Vector2d v = new Vector2d(1, 2);

        String result = v.toString();

        assertEquals("(1, 2)", result);
    }

    @Test
    void testEquals() {
        Vector2d v1 = new Vector2d(2, 3);
        Vector2d v2 = new Vector2d(2, 3);

        assertEquals(v1, v2);
    }
}