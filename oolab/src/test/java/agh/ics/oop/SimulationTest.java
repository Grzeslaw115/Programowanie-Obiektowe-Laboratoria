package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.Animal;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    @Test
    public void orientation(){
        List<Vector2d> positions = Arrays.asList(new Vector2d(0, 0), new Vector2d(0, 0));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.FORWARD, MoveDirection.RIGHT);

        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(MapDirection.NORTH, animals.get(0).getOrientation());
        assertEquals(MapDirection.EAST, animals.get(1).getOrientation());
    }

    @Test
    public void movement(){
        List<Vector2d> positions = Arrays.asList(new Vector2d(2, 2), new Vector2d(4, 4));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD);

        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.get(0).isAt(new Vector2d(1, 4)));
        assertTrue(animals.get(1).isAt(new Vector2d(4, 4)));
    }

    @Test
    public void stayWithinMapNorthWall() {
        List<Vector2d> positions = Arrays.asList(new Vector2d(0, 4), new Vector2d(0, 4));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD);

        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.get(0).isAt(new Vector2d(0, 4)));
        assertTrue(animals.get(1).isAt(new Vector2d(0, 4)));
    }

    @Test
    public void stayWithinMapSouthWall() {
        List<Vector2d> positions = Arrays.asList(new Vector2d(0, 0), new Vector2d(0, 0));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.BACKWARD, MoveDirection.BACKWARD);

        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.get(0).isAt(new Vector2d(0, 0)));
        assertTrue(animals.get(1).isAt(new Vector2d(0, 0)));
    }

    @Test
    public void stayWithinMapWestWall() {
        List<Vector2d> positions = Arrays.asList(new Vector2d(0, 0), new Vector2d(0, 0));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD);

        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.get(0).isAt(new Vector2d(0, 0)));
        assertTrue(animals.get(1).isAt(new Vector2d(0, 0)));
    }

    @Test
    public void stayWithinMapEastWall() {
        List<Vector2d> positions = Arrays.asList(new Vector2d(4, 0), new Vector2d(4, 0));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD);

        Simulation simulation = new Simulation(positions, directions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertTrue(animals.get(0).isAt(new Vector2d(4, 0)));
        assertTrue(animals.get(1).isAt(new Vector2d(4, 0)));
    }

    @Test
    public void inputParsing() {
        String[] input = {"f", "f", "r", "r", "l"};
        List<Vector2d> positions = Arrays.asList(new Vector2d(0, 0), new Vector2d(0, 0));
        List<MoveDirection> directions = Arrays.asList(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT);

        Simulation simulationParsing = new Simulation(positions, OptionsParser.parseOptions(input));
        Simulation simulationDirections = new Simulation(positions, directions);
        simulationParsing.run();
        simulationDirections.run();

        List<Animal> animalsParsing = simulationParsing.getAnimals();
        List<Animal> animalsDirections = simulationDirections.getAnimals();

        assertEquals(animalsParsing.get(0), animalsDirections.get(0));
        assertEquals(animalsParsing.get(1), animalsDirections.get(1));
    }
}