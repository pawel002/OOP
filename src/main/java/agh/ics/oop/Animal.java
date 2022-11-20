package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractWorldMapElement{
    private final List<IPositionChangeObserver> observer_list = new ArrayList<>();
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
        Vector2d oldPos = position;

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
                positionChanged(oldPos, position);
                map.moved(oldPos, position);
                return true;
            }
        }
        else if(dir == MoveDirection.BACKWARD){
            if(map.canMoveTo(position.subtract(direction.UnitVector()))) {
                position = position.subtract(direction.UnitVector());
                positionChanged(oldPos, position);
                map.moved(oldPos, position);
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

    public void addObserver(IPositionChangeObserver observer){
        observer_list.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observer_list.remove(observer);
    }

    public void positionChanged(Vector2d oldPos, Vector2d newPos){
        for(IPositionChangeObserver observer : observer_list){
            observer.positionChanged(oldPos, newPos);
        }
    }
}
