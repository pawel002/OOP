package agh.ics.oop;
import static java.lang.System.out;
import agh.ics.oop.MoveDirection;

public class World {
    public static MoveDirection[] convert(String[] args){
        int size = 0;
        for(String arg : args){
            if(arg.equals("f") || arg.equals("b") || arg.equals("r") || arg.equals("l")) {
                size++;
            }
        }

        MoveDirection[] dir = new MoveDirection[size];
        var idx = 0;
        for(String arg : args){
            switch(arg){
                case "f" -> dir[idx++] = MoveDirection.FORWARD;
                case "b" -> dir[idx++] = MoveDirection.BACKWARD;
                case "r" -> dir[idx++] = MoveDirection.RIGHT;
                case "l" -> dir[idx++] = MoveDirection.LEFT;
            }
        }

        return dir;
    }

    public static void run(MoveDirection[] args){
        for(MoveDirection arg : args){
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
        out.println("Stop");
    }
}
