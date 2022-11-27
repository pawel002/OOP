package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.gui.GuiElementBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimulationEngine implements IEngine, Runnable {
    protected List<Animal> animals = new ArrayList<>();
    private MoveDirection[] directions;
    private IWorldMap map;
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
    public void run(boolean  visualize) throws FileNotFoundException {
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

    @Override
    public void setDirections(MoveDirection[] directions_) {
        directions = directions_;
    }


    public void run(){
        // add GuiElement observers
        for(Animal animal : animals){
            GuiElementBox elementBox = new GuiElementBox(animal, 20);
            animal.addObserver(elementBox);
        }

        Platform.runLater(()->{
            // clear and make new frame
            App.clear();
            App.visualize();
        });
        // sleep
        visualizationSleep();

        // jako, ze grass nie zmienia pozycji oraz obiekt guielementbox odpowiedzialny za trawe bedzie istnial
        // tylko w instancji trawy, to nie musimy sie nim martwic.
        int count = 0;
        for(MoveDirection dir : directions) {
            animals.get(count++ % animal_count).move(dir);
            Platform.runLater(()->{
                App.clear();
                App.visualize();
            });
            visualizationSleep();
        }

    }

    private void visualizationSleep(){
        try {
            Thread.sleep(App.moveDelay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
