package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

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
        System.out.println("Start");
        List<MoveDirection> direction_array = OptionsParser.parseOptions(args);
        run(direction_array);
        System.out.println("Stop");

        Animal animal1 = new Animal();
        System.out.println(animal1);
    }
}
