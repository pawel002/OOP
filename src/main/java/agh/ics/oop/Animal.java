package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection direction = MapDirection.NORTH;

    public String toString(){
        return String.join("", "Position: ", position.toString(), ". Orientation: ", direction.toString(), ".");
    }

    public boolean isAt(Vector2d pos){
        return position.equals(pos);
    }

    public void move(MoveDirection dir){
        if(dir == MoveDirection.RIGHT)
            direction = direction.next();
        else if(dir == MoveDirection.LEFT)
            direction = direction.previous();
        else if(dir == MoveDirection.FORWARD)
            position = (position.add(direction.UnitVector()));
        else if(dir == MoveDirection.BACKWARD)
            position = (position.subtract(direction.UnitVector()));

        position = position.lowerLeft(new Vector2d(4,4)).upperRight(new Vector2d(0,0));
    }

    public Vector2d getPosition(){
        return position;
    }

    public MapDirection getDirection(){
        return direction;
    }

    public void reset(){
        position = new Vector2d(2, 2);
        direction = MapDirection.NORTH;
    }
}
