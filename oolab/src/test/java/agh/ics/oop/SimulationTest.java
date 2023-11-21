package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    public void orientation(){
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = Arrays.asList(new Vector2d(0, 0), new Vector2d(0, 1));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.FORWARD, MoveDirection.RIGHT);

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(MapDirection.NORTH, animals.get(0).getOrientation());
        assertEquals(MapDirection.EAST, animals.get(1).getOrientation());
    }


    @Test
    public void movement(){
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = Arrays.asList(new Vector2d(2, 2), new Vector2d(4, 4));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD);

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.get(0).isAt(new Vector2d(1, 4)));
        assertTrue(animals.get(1).isAt(new Vector2d(4, 4)));
    }

    @Test
    public void stayWithinMapNorthWall() {
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = Arrays.asList(new Vector2d(1, 4), new Vector2d(0, 4));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD);

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.get(0).isAt(new Vector2d(1, 4)));
        assertTrue(animals.get(1).isAt(new Vector2d(0, 4)));
    }

    @Test
    public void stayWithinMapSouthWall() {
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = Arrays.asList(new Vector2d(0, 0), new Vector2d(1, 0));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.BACKWARD, MoveDirection.BACKWARD);

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.get(0).isAt(new Vector2d(0, 0)));
        assertTrue(animals.get(1).isAt(new Vector2d(1, 0)));
    }

    @Test
    public void stayWithinMapWestWall() {
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = Arrays.asList(new Vector2d(0, 0), new Vector2d(0, 1));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD);

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.get(0).isAt(new Vector2d(0, 0)));
        assertTrue(animals.get(1).isAt(new Vector2d(0, 1)));
    }

    @Test
    public void stayWithinMapEastWall() {
        WorldMap map = new RectangularMap(5, 5);
        List<Vector2d> positions = Arrays.asList(new Vector2d(4, 0), new Vector2d(4, 1));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD);

        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.get(0).isAt(new Vector2d(4, 0)));
        assertTrue(animals.get(1).isAt(new Vector2d(4, 1)));
    }

    @Test
    public void inputParsing() {
        String[] input = {"f", "f", "r", "r", "l"};
        WorldMap mapParsing = new RectangularMap(6, 6);
        WorldMap mapDirections = new RectangularMap(6, 6);
        List<Vector2d> positions = Arrays.asList(new Vector2d(0, 0), new Vector2d(4, 4));
        List<MoveDirection> directions = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT);

        Simulation simulationParsing = new Simulation(positions, OptionsParser.parseOptions(input), mapParsing);
        Simulation simulationDirections = new Simulation(positions, directions, mapDirections);
        simulationParsing.run();
        simulationDirections.run();

        List<Animal> animalsParsing = simulationParsing.getAnimals();
        List<Animal> animalsDirections = simulationDirections.getAnimals();

        assertEquals(animalsParsing.get(0), animalsDirections.get(0));
        assertEquals(animalsParsing.get(1), animalsDirections.get(1));
    }
}