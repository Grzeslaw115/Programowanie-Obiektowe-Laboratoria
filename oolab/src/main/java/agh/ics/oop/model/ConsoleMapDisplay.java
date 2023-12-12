package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updatesCounter = 0;
    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) { //bez synchronized race conditions
        updatesCounter++;
        System.out.println("Update: " + updatesCounter);
        System.out.println("Operation: " + message);
        System.out.println("Map ID: " + worldMap.getId());
        System.out.println(worldMap);
    }
}