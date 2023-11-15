package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private List<Vector2d> positions;
    private List<MoveDirection> directions;
    private List<Animal> animals;

    public List<Animal> getAnimals() {
        return animals;
    }

    public Simulation(List<Vector2d> positions, List<MoveDirection> directions){
        this.positions = positions;
        this.directions = directions;
        this.animals = new ArrayList<>();

        for(Vector2d starting_position : positions) {
            this.animals.add(new Animal(starting_position));
        }
    }

    public void run(){
        int how_many_animals = animals.size();
        int how_many_moves = directions.size();

        for(int i = 0; i < how_many_moves; i++){
            Animal current_animal = animals.get(i % how_many_animals);
            MoveDirection current_direction = directions.get(i);

            current_animal.move(current_direction);
            System.out.println("Zwierzę " + (i % how_many_animals) + ": " + current_animal);
        }
    }
}