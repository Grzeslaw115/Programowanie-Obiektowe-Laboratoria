package agh.ics.oop.model;

import agh.ics.oop.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals;
    protected MapVisualizer mapVisualizer;
    protected final List<MapChangeListener> listeners = new ArrayList<>();

    public void addMapChangeListener(MapChangeListener listener) {
        listeners.add(listener);
    }

    public void removeMapChangeListener(MapChangeListener listener) {
        listeners.remove(listener);
    }

    protected void mapChanged(String message) {
        for (MapChangeListener listener : listeners) {
            listener.mapChanged(this, message);
        }
    }

    public AbstractWorldMap() {
        this.animals = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this);
    }

    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        Vector2d animalPosition = animal.getPosition();
        if (this.isOccupied(animalPosition) && !(this.objectAt(animalPosition) instanceof Grass)) {
            throw new PositionAlreadyOccupiedException(animalPosition);
        }
        animals.put(animalPosition, animal);
        mapChanged("Animal placed at " + animalPosition);
    }

    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = animal.getPosition();
        animal.move(direction, this);
        Vector2d newPosition = animal.getPosition();

        if (!oldPosition.equals(newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
            mapChanged("Animal moved from " + oldPosition + " to " + newPosition);
        }
        if (direction == MoveDirection.LEFT){
            mapChanged("Animal turned left");
        }

        if (direction == MoveDirection.RIGHT){
            mapChanged("Animal turned right");
        }
    }

    @Override
    public String toString() {
        Boundary boundary = getCurrentBounds();
        return mapVisualizer.draw(boundary.lowerLeft(), boundary.upperRight());
    }

    public abstract Boundary getCurrentBounds();

    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    public Collection<WorldElement> getElements() {
        return Collections.unmodifiableCollection(animals.values());
    }

    public abstract boolean canMoveTo(Vector2d position);
}