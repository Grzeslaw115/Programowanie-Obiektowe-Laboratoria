package agh.ics.oop;

import agh.ics.oop.exceptions.PositionAlreadyOccupiedException;
import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable {

    private final List<MoveDirection> directions;
    private final List<Animal> animals;
    private final List<Vector2d> positions;
    private final WorldMap worldMap;
    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap worldMap) {
        this.directions = directions;
        this.animals = new ArrayList<>();
        this.worldMap = worldMap;
        this.positions = positions;
    }

    public void run(){

        for (Vector2d startingPosition : positions) {
            try {
                Animal animal = new Animal(startingPosition);
                worldMap.place(animal);
                animals.add(animal);
            } catch (PositionAlreadyOccupiedException e) {
                System.err.println("Animal can't be placed at: " + startingPosition);
            }
        }

        int how_many_animals = animals.size();
        int how_many_moves = directions.size();

        for(int i = 0; i < how_many_moves; i++){
            Animal current_animal = animals.get(i % how_many_animals);
            MoveDirection current_direction = directions.get(i);

            worldMap.move(current_animal, current_direction);
        }
    }
}