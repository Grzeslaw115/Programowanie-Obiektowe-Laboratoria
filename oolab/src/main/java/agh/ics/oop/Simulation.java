package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final List<MoveDirection> directions;
    private final List<Animal> animals;
    private final WorldMap worldMap;

    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions, WorldMap worldMap){
        this.directions = directions;
        this.animals = new ArrayList<>();
        this.worldMap = worldMap;

        for (Vector2d startingPosition : positions) {
            Animal animal = new Animal(startingPosition);
            if (worldMap.place(animal)) {
                animals.add(animal);
            }
        }
    }

    public void run(){
        int how_many_animals = animals.size();
        int how_many_moves = directions.size();

        for(int i = 0; i < how_many_moves; i++){
            Animal current_animal = animals.get(i % how_many_animals);
            MoveDirection current_direction = directions.get(i);

            worldMap.move(current_animal, current_direction);

            System.out.println("Zwierzę " + (i % how_many_animals) + ": " + current_animal.getPosition());
            System.out.println(worldMap);
        }
    }
}