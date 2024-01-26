package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void parseOptions() {
        String[] validArgs = { "f", "b", "r", "l" };
        List<MoveDirection> validResult = OptionsParser.parseOptions(validArgs);
        List<MoveDirection> validExpected = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT);
        assertEquals(validExpected, validResult);

        String[] empty = {""};
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parseOptions(empty));

        String[] words = {"litwo ojczyzno moja"};
        assertThrows(IllegalArgumentException.class, () -> OptionsParser.parseOptions(words));
    }
}