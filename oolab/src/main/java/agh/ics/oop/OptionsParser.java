package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parseOptions(String[] args) {
        List<MoveDirection> directions = new ArrayList<>();

        for (String direction : args) {
            switch (direction) {
                case "f" -> directions.add(MoveDirection.FORWARD);
                case "b" -> directions.add(MoveDirection.BACKWARD);
                case "r" -> directions.add(MoveDirection.RIGHT);
                case "l" -> directions.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(direction + " is not a legal move specification");
            }
        }
        return directions;
    }
}