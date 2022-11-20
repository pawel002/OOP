package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;


public class App extends javafx.application.Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw());
//        MoveDirection[] directions = OptionsParser.parse(List.of("f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"));
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,3) };

        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        // wizualizacja zawarta w metodzie visualize klasy simulationEngine
        // .run(true) przeprowadza symulacje w consoli
//        engine.run(true);
        engine.visualize(primaryStage);
    }
}
