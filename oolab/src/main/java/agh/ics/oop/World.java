package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = OptionsParser.parseOptions(args);
            List<Simulation> simulations = getSimulations(directions);

            SimulationEngine simulationEngine = new SimulationEngine(simulations);
            simulationEngine.run();

        } catch (IllegalArgumentException e) {
            System.err.println("Error in parsing options");
        }
    }

    private static List<Simulation> getSimulations(List<MoveDirection> directions) {
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(2,4));
        List<Simulation> simulations = new ArrayList<>();
        ConsoleMapDisplay consoleMapDisplay = new ConsoleMapDisplay();

        for(int i = 0; i < 1000; i++){
            AbstractWorldMap worldMap = new RectangularMap(10,10);
            worldMap.addMapChangeListener(consoleMapDisplay);
            Simulation simulation = new Simulation(positions, directions, worldMap);
            simulations.add(simulation);
        }
        return simulations;
    }
}