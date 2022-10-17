package agh.ics.oop;
import static java.lang.System.out;

// Aby zwierzęta nie nachodziły na siebie, wystarczy przed wykonaniem ruchy przez dane zwierze
// przejść po tablicy zawierającej wszytskie zwierzęta i sprawdzić czy któreś z nich znajduje
// się na pozycji na której zwierze znajdzie się po ruchu. Można to wykonać za pomocą isAt.
// Jeżeli kolizja nie wystąpi można wykonać ruch.

public class World {

    public static void main(String[] args){
        out.println("Start.");

        MoveDirection[] MoveSet = OptionsParser.parse(args);
        Animal dog = new Animal();
        for(MoveDirection Move : MoveSet){
            dog.move(Move);
            out.println(dog);
        }

        out.println("Stop.");
    }
}
