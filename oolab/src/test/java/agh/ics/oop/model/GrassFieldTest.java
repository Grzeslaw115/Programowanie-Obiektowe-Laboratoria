package agh.ics.oop.model;

import agh.ics.oop.exceptions.PositionAlreadyOccupiedException;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    private GrassField grassField;

    @Test
    void place() {
        grassField = new GrassField(10);
        Animal animal1 = new Animal(new Vector2d(2, 2));
        try {
            grassField.place(animal1);
        } catch (PositionAlreadyOccupiedException e) { //wyjatek nie powinien zostac tu rzucony
            fail();
        }

        Animal animal2 = new Animal(new Vector2d(2, 2));
        assertThrows(PositionAlreadyOccupiedException.class, () -> grassField.place(animal2));
    }

    @Test
    void move() throws PositionAlreadyOccupiedException {
        grassField = new GrassField(10);

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
    void isOccupied() throws PositionAlreadyOccupiedException {
        grassField = new GrassField(10);


        grassField.place(new Animal(new Vector2d(2, 2)));
        assertTrue(grassField.isOccupied(new Vector2d(2, 2)));

        grassField = new GrassField(0);
        assertFalse(grassField.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    void objectAt() throws PositionAlreadyOccupiedException {
        grassField = new GrassField(10);

        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);
        assertEquals(animal, grassField.objectAt(new Vector2d(2, 2)));

        if(grassField.getGrassPatches().contains(new Vector2d(3, 3))){
            assertTrue(grassField.objectAt(new Vector2d(3, 3)) instanceof Grass);
        }
        else{
            assertNull(grassField.objectAt(new Vector2d(3, 3)));
        }
    }

    @Test
    void getElements() throws PositionAlreadyOccupiedException {
        grassField = new GrassField(10);

        Collection<WorldElement> elements = grassField.getElements();
        assertFalse(elements.isEmpty());

        Animal animal = new Animal(new Vector2d(2, 2));
        grassField.place(animal);
        elements = grassField.getElements();
        assertTrue(elements.contains(animal));
    }
}