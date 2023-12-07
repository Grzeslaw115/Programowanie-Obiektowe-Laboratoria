package agh.ics.oop.model;

import agh.ics.oop.exceptions.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    private RectangularMap map;

    @BeforeEach
    void setUp() {
        map = new RectangularMap(5, 5);
    }

    @Test
    void place() {
        Animal animal1 = new Animal(new Vector2d(2, 2));
        try {
            map.place(animal1);
        } catch (PositionAlreadyOccupiedException e) { //wyjatek nie powinien zostac tutaj rzucony
            fail();
        }

        Animal animal2 = new Animal(new Vector2d(2, 2));
        assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animal2));
    }

    @Test
    void move() throws PositionAlreadyOccupiedException {
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertSame(animal.getOrientation(), MapDirection.NORTH);

        map.move(animal, MoveDirection.LEFT);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertSame(animal.getOrientation(), MapDirection.WEST);

        map.move(animal, MoveDirection.RIGHT);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertSame(animal.getOrientation(), MapDirection.NORTH);

        map.move(animal, MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertSame(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void isOccupied() throws PositionAlreadyOccupiedException {
        assertFalse(map.isOccupied(new Vector2d(2, 2)));
        map.place(new Animal(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void objectAt() throws PositionAlreadyOccupiedException {
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);
        assertEquals(animal, map.objectAt(new Vector2d(2, 2)));
        assertNull(map.objectAt(new Vector2d(3, 3)));
    }

    @Test
    void getElements() throws PositionAlreadyOccupiedException {
        Collection<WorldElement> elements = map.getElements();
        assertTrue(elements.isEmpty());

        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);
        elements = map.getElements();
        assertTrue(elements.contains(animal));
    }
}