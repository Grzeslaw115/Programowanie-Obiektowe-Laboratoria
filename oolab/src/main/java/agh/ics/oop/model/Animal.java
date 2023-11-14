package agh.ics.oop.model;

import java.util.Objects;

public class Animal {
    private static final int MIN_POSITION = 0;
    private static final int MAX_POSITION = 4;

    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;

    public Animal() {
        this.position = new Vector2d(2, 2);
    }

    public Animal(Vector2d position) {
        this.position = position;
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public static boolean isBetween(int value, int minValue, int maxValue) {
        return value >= minValue && value <= maxValue;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();

            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (isPositionValid(newPosition)) {
                    position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector().opposite());
                if (isPositionValid(newPosition)) {
                    position = newPosition;
                }
            }
        }
    }

    private boolean isPositionValid(Vector2d newPosition) {
        return isBetween(newPosition.getX(), MIN_POSITION, MAX_POSITION) &&
                isBetween(newPosition.getY(), MIN_POSITION, MAX_POSITION);
    }

    @Override
    public String toString() {
        return "pozycja = " + position.toString() +
                ", orientacja = " + orientation;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(position, animal.position) && orientation == animal.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, orientation);
    }
}