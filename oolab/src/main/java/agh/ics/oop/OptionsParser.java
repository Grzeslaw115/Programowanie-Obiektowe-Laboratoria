package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] parseOptions(String[] args) {
        MoveDirection[] directions = new MoveDirection[args.length];
        int validDirectionCount = 0;

        for (String direction : args) {
            MoveDirection currDirection = null;

            switch (direction) {
                case "f" -> currDirection = MoveDirection.FORWARD;
                case "b" -> currDirection = MoveDirection.BACKWARD;
                case "r" -> currDirection = MoveDirection.RIGHT;
                case "l" -> currDirection = MoveDirection.LEFT;
            }

            if (currDirection != null) {
                directions[validDirectionCount] = currDirection;
                validDirectionCount++;
            }

        }

        return java.util.Arrays.copyOf(directions, validDirectionCount);
    }
}