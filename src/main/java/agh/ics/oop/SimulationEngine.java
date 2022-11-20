package agh.ics.oop;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine {
    protected List<Animal> animals = new ArrayList<>();
    public MoveDirection[] directions;
    public IWorldMap map;
    public int animal_count;

    public SimulationEngine(MoveDirection[] directions_, IWorldMap map_, Vector2d[] positions){
        directions = directions_;
        map = map_;
        int placed_animals = 0;
        for(Vector2d v : positions){
            Animal a = new Animal(map, v);
            if(map.place(a)){
                animals.add(a);
                placed_animals ++;
            }
        }
        animal_count = placed_animals;
    }

    @Override
    public void run(boolean  visualize){
        int count = 0;
        for(MoveDirection dir : directions) {
            if (visualize)
                System.out.println(map);
            animals.get(count++ % animal_count).move(dir);
        }
        if (visualize)
            System.out.println(map);
    }

    @Override
    public Animal getAnimal(int i){
        return animals.get(i %  animal_count);
    }

    public void visualize(Stage primaryStage) throws InterruptedException {
        System.out.println(map);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPrefSize(1080, 720);
        grid.setGridLinesVisible(true);

        Scene scene = new Scene(grid, 1080, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Programowanie obiektowe");

        int width = map.getTopRight().x - map.getBottomLeft().x +  2;
        int height = map.getTopRight().y - map.getBottomLeft().y + 2;

        Label[] Labels = new Label[width*height];
        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                AbstractWorldMapElement elem = (AbstractWorldMapElement) map.objectAt(new Vector2d(map.getBottomLeft().x + j - 1, height  + map.getBottomLeft().y - i-2));
                if(i == height - 1)
                    Labels[i*width + j] = new Label(String.valueOf(j + map.getBottomLeft().x-1));
                else if(j == 0)
                    Labels[i*width + j] = new Label(String.valueOf(height - 2 - i + map.getBottomLeft().y));
                else if (elem  != null)
                    Labels[i*width + j] = new Label(elem.toString());
                else
                    Labels[i*width + j] = new Label("");

                Labels[i*width + j].setAlignment(Pos.CENTER);
                Labels[i*width + j].setMinSize(50, 50);
                grid.add(Labels[i*width + j], j, i, 1,  1);
                GridPane.setHalignment(Labels[i*width + j], HPos.CENTER);
            }
        }
        Labels[width*height - width].setText("y/x");
        primaryStage.show();
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
//            int count = 0;
//
//            @Override
//            public void handle(ActionEvent event) {
//                MoveDirection move = directions[count];
//                Animal animal = animals.get(count++ % animal_count);
//                Vector2d pos = animal.getPosition();
//                if(animal.move(move)){
//                    Vector2d new_pos = animal.getPosition();
//                    Labels[pos.x + 1+ (height - 2 - pos.y) * width].setText("");
//                    Labels[new_pos.x + 1+ (height - 2 - new_pos.y) * width].setText(animal.toString());
//                }
//                else{
//                    System.out.println("Collision detected. Cannot move.");
//                }
//                primaryStage.show();
//            }
//        }));
//        timeline.setCycleCount(directions.length);
//        timeline.play();
    }
}
