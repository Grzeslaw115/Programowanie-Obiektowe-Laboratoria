package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap{
    private final Map<Vector2d, Animal> animals;
    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.animals = new HashMap<>();
    }

    public static boolean isBetween(int value, int minValue, int maxValue) {
        return value >= minValue && value <= maxValue;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        boolean within_borders = isBetween(position.getX(), 0, width - 1) &&
                isBetween(position.getY(), 0, height - 1);
        return !isOccupied(position) && within_borders;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if (canMoveTo(animalPosition) && !isOccupied(animalPosition)) {
            animals.put(animalPosition, animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (!oldPosition.equals(newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Vector2d lowerLeft = new Vector2d(0, 0);
        Vector2d upperRight = new Vector2d(width - 1, height - 1);
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
