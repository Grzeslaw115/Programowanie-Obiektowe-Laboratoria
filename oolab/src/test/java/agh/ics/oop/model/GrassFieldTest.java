package agh.ics.oop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    private GrassField grassField;

    @BeforeEach
    void setUp() {
        grassField = new GrassField(10);
    }

    @Test
    void place() {
        Animal animal1 = new Animal(new Vector2d(2, 2));
        assertTrue(grassField.place(animal1));
        assertFalse(grassField.place(animal1));

        Animal animal2 = new Animal(new Vector2d(2, 2));
        assertFalse(grassField.place(animal2));
    }

    @Test
    void move() {
        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);
        grassField.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertSame(animal.getOrientation(), MapDirection.NORTH);

        grassField.move(animal, MoveDirection.LEFT);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertSame(animal.getOrientation(), MapDirection.WEST);

        grassField.move(animal, MoveDirection.RIGHT);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
        assertSame(animal.getOrientation(), MapDirection.NORTH);

        grassField.move(animal, MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertSame(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    void isOccupied() {
        assertFalse(grassField.isOccupied(new Vector2d(2, 2)));
        grassField.place(new Animal(new Vector2d(2, 2)));
        assertTrue(grassField.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void objectAt() {
        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 2)));
        assertNull(grassField.objectAt(new Vector2d(3, 3)));
    }

    @Test
    void getElements() {
        Collection<WorldElement> elements = grassField.getElements();
        assertFalse(elements.isEmpty());

        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);
        elements = grassField.getElements();
        assertTrue(elements.contains(animal));
    }


}