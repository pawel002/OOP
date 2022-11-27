package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox implements IPositionChangeObserver {
    IMapElement element;
    int size;
    VBox box;

    public GuiElementBox(IMapElement elem, int size_){
        try {
            size = size_;
            element = elem;
            Image image = new Image(new FileInputStream(element.getResName()));
            ImageView img = new ImageView(image);
            img.setFitWidth(size);
            img.setFitHeight(size);
            box = new VBox();
            box.setMinSize(50,50);
            box.getChildren().add(img);
            if(element.identifier() == 0)
                box.getChildren().add(new Label("Grass"));
            else
                box.getChildren().add(new Label(element.getPosition().toString()));
            box.setAlignment(Pos.CENTER);
        } catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void update(){
        try {
            Image image = new Image(new FileInputStream(element.getResName()));
            ImageView img = new ImageView(image);
            img.setFitWidth(size);
            img.setFitHeight(size);
            box = new VBox();
            box.getChildren().add(img);
            box.setMinSize(50, 50);
            if(element.identifier() == 0)
                box.getChildren().add(new Label("Grass"));
            else
                box.getChildren().add(new Label(element.getPosition().toString()));
            box.setAlignment(Pos.CENTER);
        } catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public VBox getBox() {
        return box;
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        this.update();
    }
}
