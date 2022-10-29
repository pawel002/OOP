package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection direction = MapDirection.NORTH;
    private IWorldMap map;

    Animal(IWorldMap map_){
        map = map_;
    }

    Animal(IWorldMap map_, Vector2d pos_){
        map = map_;
        position = pos_;
    }

    public String toString(){
        return direction.toString();
    }

    public boolean isAt(Vector2d pos){
        return position.equals(pos);
    }

    public boolean move(MoveDirection dir){
        if(dir == MoveDirection.RIGHT) {
            direction = direction.next();
            return true;
        }
        else if(dir == MoveDirection.LEFT) {
            direction = direction.previous();
            return true;
        }
        else if(dir == MoveDirection.FORWARD){
            if(map.canMoveTo(position.add(direction.UnitVector()))) {
                position = position.add(direction.UnitVector());
                return true;
            }
        }
        else if(dir == MoveDirection.BACKWARD){
            if(map.canMoveTo(position.subtract(direction.UnitVector()))) {
                position = position.subtract(direction.UnitVector());
                return true;
            }
        }
        return false;
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
