package agh.ics.oop;
import static java.lang.System.out;
import agh.ics.oop.Directions.Direction;

public class World {
    public static Direction[] convert(String[] args){
        int size = 0;
        for(String arg : args){
            if(arg.equals("f") || arg.equals("b") || arg.equals("r") || arg.equals("l")) {
                size++;
            }
        }

        Direction[] dir = new Direction[size];
        var idx = 0;
        for(String arg : args){
            switch(arg){
                case "f" -> dir[idx++] = Direction.FORWARD;
                case "b" -> dir[idx++] = Direction.BACKWARD;
                case "r" -> dir[idx++] = Direction.RIGHT;
                case "l" -> dir[idx++] = Direction.LEFT;
            }
        }

        return dir;
    }

    public static void run(Direction[] args){
        for(Direction arg : args){
            switch(arg){
                case FORWARD -> out.println("forwards");
                case BACKWARD -> out.println("backwards");
                case RIGHT -> out.println("right");
                case LEFT -> out.println("left");
            }
        }
    }

    public static void main(String[] args){
        out.println("Start");
        run(convert(args));
        var L = 0;
        out.println("Stop");
    }
}
