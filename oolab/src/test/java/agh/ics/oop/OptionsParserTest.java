package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseOptions() {
        String[] args = { "f", "b", "r", "x", "l", "y" };

        List<MoveDirection> result = OptionsParser.parseOptions(args);

        List<MoveDirection> expected = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT);
        assertEquals(expected, result);
    }
}