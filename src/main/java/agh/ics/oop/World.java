package agh.ics.oop;
import static java.lang.System.out;

// 10 i 11
// dodanie interfejsu dla elementow swiata czyli trawy i zwierząt znacznie ułatwia implemenetacja metod
// dla map swiata. dzieki temu nie musimy korzystać z 2 ososbnych list dla trawy i zwierzat osobno
// a tylko z jednej, wspolnej. Ponadto pozwala to pozbyć się niepotrzebnego kodu oraz ułatwia uogólnienie i dodawanie kolejnych
// elementów swiata. Dlatego zdecydowałem sie zaimplementować IMapElement oraz AbstarctWorldMapElement
// zaimplementowałem także zadanie 12

public class World {

    public static void main(String[] args) throws InterruptedException {
        out.println("Start.");

        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };

        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        // wizualizacja nie posiada implementacji dla dynamicznej mapy
        // engine.visualize();

        out.println("Stop.");
    }
}
