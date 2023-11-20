package agh.ics.oop.model;

import java.util.Objects;

public class Animal {
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

    public void move(MoveDirection direction, MoveValidator validator) {
        switch (direction) {
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();

            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (validator.canMoveTo(newPosition)) {
                    position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector().opposite());
                if (validator.canMoveTo(newPosition)) {
                    position = newPosition;
                }
            }
        }
    }

    @Override
    public String toString() {
        switch (orientation) {
            case NORTH -> {
                return "N";
            }
            case EAST -> {
                return "E";
            }
            case SOUTH -> {
                return "S";
            }
            case WEST -> {
                return "W";
            }
        }
        return "";
    }


    public MapDirection getOrientation() {
        return orientation;
    }
    public Vector2d getPosition() {
        return this.position;
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