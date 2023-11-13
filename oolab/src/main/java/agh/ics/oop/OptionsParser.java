package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parseOptions(String[] args) {
        List<MoveDirection> directions = new ArrayList<>();

        for (String direction : args) {
            MoveDirection currDirection = null;

            switch (direction) {
                case "f" -> currDirection = MoveDirection.FORWARD;
                case "b" -> currDirection = MoveDirection.BACKWARD;
                case "r" -> currDirection = MoveDirection.RIGHT;
                case "l" -> currDirection = MoveDirection.LEFT;
            }

            if (currDirection != null) {
                directions.add(currDirection);
            }

        }

        return directions;
    }
}