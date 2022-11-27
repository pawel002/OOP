package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.List;


public class App extends javafx.application.Application{

    public static Stage primaryStage;
    public static GridPane grid;
    public static AbstractWorldMap map;
    public static final int moveDelay = 500;

    @Override
    public void start(Stage primaryStage_){
        try {
            primaryStage = primaryStage_;
            String[] args = getParameters().getRaw().toArray(new String[0]);
            MoveDirection[] directions = OptionsParser.parse(List.of(args));
            map = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,3) };
            SimulationEngine engine = new SimulationEngine(directions, map, positions);

            grid = new GridPane();
            grid.setGridLinesVisible(true);
            grid.setAlignment(Pos.CENTER);
            grid.setPrefSize(600, 600);
            grid.setGridLinesVisible(true);
            primaryStage.setTitle("Programowanie obiektowe");
            visualize();


            TextField input = new TextField();
            if (args.length == 0) {
                input.setPromptText("Args");
            } else {
                input.setPromptText(String.join(" ", args));
            }
            input.setPrefColumnCount(15);

            Button Start = new Button("START");
            Start.setOnMouseClicked(event -> {
                if ((input.getText() != null && !input.getText().isEmpty())) {
                    try {
                        engine.setDirections(new OptionsParser().parse(List.of(input.getText().split(" "))));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.toString());
                        Platform.exit();
                    }
                }
                Thread engineThread = new Thread(engine);
                engineThread.start();
                Start.setDisable(true);
                input.setDisable(true);
            });

            HBox startBox = new HBox(2, Start, input);
            startBox.setAlignment(Pos.CENTER);
            VBox app = new VBox(2, grid, startBox);
            Scene scene = new Scene(app, 720, 720);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IllegalArgumentException exception) {
            System.out.println(exception.toString());
            Platform.exit();
        }
    }

    public static void visualize(){
//        System.out.println(map);
        grid.setGridLinesVisible(true);
        int width = map.getTopRight().x - map.getBottomLeft().x +  2;
        int height = map.getTopRight().y - map.getBottomLeft().y + 2;

        Label[] Labels = new Label[width*height];
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                AbstractWorldMapElement elem = (AbstractWorldMapElement) map.objectAt(new Vector2d(map.getBottomLeft().x + j - 1, height  + map.getBottomLeft().y - i-2));
                if (elem  != null){
                    GuiElementBox elementBox = new GuiElementBox(elem, 20);
                    grid.add(elementBox.getBox(), j, i, 1,  1);
                    GridPane.setHalignment(elementBox.getBox(), HPos.CENTER);
                }
                else {
                    // numeracja osi x
                    if (i == height - 1){
                        Labels[i * width + j] = new Label(String.valueOf(j + map.getBottomLeft().x - 1));
                        Labels[i*width + j].setAlignment(Pos.CENTER);
                        Labels[i*width + j].setMinSize(50, 50);
                        grid.add(Labels[i*width + j], j, i, 1,  1);
                        GridPane.setHalignment(Labels[i*width + j], HPos.CENTER);
                    }
                    // numeracja osi y
                    else if (j == 0){
                        Labels[i * width + j] = new Label(String.valueOf(height - 2 - i + map.getBottomLeft().y));
                        Labels[i*width + j].setAlignment(Pos.CENTER);
                        Labels[i*width + j].setMinSize(50, 50);
                        grid.add(Labels[i*width + j], j, i, 1,  1);
                        GridPane.setHalignment(Labels[i*width + j], HPos.CENTER);
                    }
                }
            }
        }
        Labels[width*height - width].setText("y/x");
    }

    public static void clear() {
        grid.setGridLinesVisible(false);
        grid.getChildren().clear();
    }
}
