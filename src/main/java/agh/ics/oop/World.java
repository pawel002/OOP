package agh.ics.oop;
import static java.lang.System.out;

public class World {

    public static void main(String[] args) throws InterruptedException {
        out.println("Start.");

        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        // Jeżeli chcemy wizualizacje w consoli możemy uzyc run.
        // engine.run();
        engine.visualize();

        out.println("Stop.");
    }
}
