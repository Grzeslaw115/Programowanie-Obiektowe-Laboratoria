package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;

import java.util.List;

public class World {
    public static void run(List<MoveDirection> args) {

        for (MoveDirection direction : args) {
            String message = switch (direction) {
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tylu";
                case LEFT -> "Zwierzak skreca w lewo";
                case RIGHT -> "Zwierzak skreca w prawo";
            };
            System.out.println(message);
        }
    }


    public static void main(String[] args) {
        /*
        System.out.println("Start");
        List<MoveDirection> directionList = OptionsParser.parseOptions(args);
        run(directionList);
        System.out.println("Stop");

        List<MoveDirection> directions = OptionsParser.parseOptions(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions); simulation.run();
         */

        Animal animal = new Animal();
        System.out.println(animal);
    }
}
