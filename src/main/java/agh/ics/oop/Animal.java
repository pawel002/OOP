package agh.ics.oop;

public class Animal extends AbstractWorldMapElement{
    private MapDirection direction = MapDirection.NORTH;
    private final IWorldMap map;

    Animal(IWorldMap map_, Vector2d pos_){
        map = map_;
        position = pos_;
    }

    public String toString(){
        return direction.toString();
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

    public MapDirection getDirection(){
        return direction;
    }

    @Override
    public int identifier(){
        return 1;
    }
}
