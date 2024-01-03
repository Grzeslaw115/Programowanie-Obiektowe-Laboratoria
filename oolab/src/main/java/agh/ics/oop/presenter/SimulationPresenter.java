package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap worldMap;
    final double CELL_WIDTH = 50;
    final double CELL_HEIGHT = 50;

    @FXML
    private TextField inputTextField;
    @FXML
    private GridPane mapGrid;
    @FXML
    private Label moveDescriptionLabel;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    public void drawMap() {
        clearGrid();

        Boundary bounds = ((AbstractWorldMap) worldMap).getCurrentBounds();
        int width = bounds.upperRight().getX() - bounds.lowerLeft().getX() + 1;
        int height = bounds.upperRight().getY() - bounds.lowerLeft().getY() + 1;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                WorldElement objectAt = worldMap.objectAt(new Vector2d(x, y));
                if (objectAt != null){
                    Label label = new Label(objectAt.toString());
                    mapGrid.add(label, x, height - y - 1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
                else{
                    Label label = new Label("");
                    mapGrid.add(label, x, height - y - 1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }

        for (int x = 0; x < width; x++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
        for (int y = 0; y < height; y++) {
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        Platform.runLater(() -> {
            moveDescriptionLabel.setText(message);
            drawMap();
        });
    }

    @FXML
    private void onSimulationStartClicked() {
        String input = inputTextField.getText();
        String[] moves = input.split(" ");
        List<MoveDirection> directions = OptionsParser.parseOptions(moves);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,3));

        AbstractWorldMap worldMap = new GrassField(5);
        setWorldMap(worldMap);
        worldMap.addMapChangeListener(this);

        Simulation simulation = new Simulation(positions, directions, worldMap);
        SimulationEngine simulationEngine = new SimulationEngine(List.of(simulation));
        simulationEngine.runAsync();
    }
}