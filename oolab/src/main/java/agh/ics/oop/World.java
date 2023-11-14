package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    public static void run(MoveDirection[] args) {

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
        MoveDirection[] direction_array = OptionsParser.parseOptions(args);
        run(direction_array);
        System.out.println("Stop");
    }
}
