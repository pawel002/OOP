package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import static java.lang.System.out;

// 1. Aby zobaczyć wizualizacje z JavaFX należy odkomentować linijke engine.visualize(primaryStage); i zakomentować engine.run(true); w klasie App
// 2. W MapBoundary przechowywać wystarczy same Vector2D, ponieważ interesuje nas tylko pozycja, a nie
// typ obiektu podczas generowania wizualizacji
// 3. Według mnie implementacja wizualizacji w klasie simulationEngine ma wiekszy sens, ponieważ ta klasa posiada
// dostęp do directions, positions oraz map

public class World {

    public static void main(String[] args) throws InterruptedException {
        out.println("Start.");
        Application.launch(App.class, args);
        out.println("Stop.");
    }
}
