package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements Runnable {
    private final List<Simulation> simulations;
    private ExecutorService executorService;

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync(){
        for(Simulation simulation : simulations) {
            simulation.run();
        }
    }

    /* DO RACE CONDITIONS
    private final List<Thread> simulationThreads = new ArrayList<>();

    public void runAsync(){
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            simulationThreads.add(thread);
            thread.start();
        }
    }

    public void awaitSimulationsEnd() {
        for (Thread thread : simulationThreads) {
            try {
                thread.join();
            } catch (InterruptedException ignored) {}
        }
    }
     */
    public void runAsync(){
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            thread.start();
        }
    }

    public void runAsyncInThreadPool(){
        this.executorService = Executors.newFixedThreadPool(4);
        for (Simulation simulation : simulations) {
            executorService.submit(simulation);
        }
    }

    public void awaitSimulationsEnd() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ignored) {}
    }

    @Override
    public void run() {
        runAsyncInThreadPool();
        awaitSimulationsEnd();
        System.out.println("All simulations have finished.");
    }
}