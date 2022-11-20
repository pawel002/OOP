package agh.ics.oop;

import java.util.List;

public class OptionsParser {
    public static MoveDirection[] parse(List<String> args) {
        int size = 0;
        for (String arg : args) {
            if (arg.equals("f") || arg.equals("b") || arg.equals("r") || arg.equals("l") ||
                    arg.equals("forward") || arg.equals("backward") || arg.equals("right") || arg.equals("left")) {
                size++;
            }
        }
        MoveDirection[] Dirs = new MoveDirection[size];
        var idx = 0;
        for (String arg : args) {
            switch (arg) {
                case "f", "forward" -> Dirs[idx++] = MoveDirection.FORWARD;
                case "b", "backward" -> Dirs[idx++] = MoveDirection.BACKWARD;
                case "r", "right" -> Dirs[idx++] = MoveDirection.RIGHT;
                case "l", "left" -> Dirs[idx++] = MoveDirection.LEFT;
                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }
        return Dirs;
    }
}
