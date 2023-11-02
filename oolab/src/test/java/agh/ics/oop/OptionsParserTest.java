package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseOptions() {
        String[] args = { "f", "b", "r", "x", "l", "y" };

        MoveDirection[] result = OptionsParser.parseOptions(args);

        MoveDirection[] expected = { MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT };
        assertArrayEquals(expected, result);
    }
}