package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap{
    protected Map<Vector2d, AbstractWorldMapElement> hashed_elements = new HashMap<>();
    protected IPositionChangeObserver observer = new MapBoundary();

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public boolean place(Animal animal) {
        if(!this.isOccupied(animal.getPosition())){
            hashed_elements.put(animal.getPosition(), animal);
            observer.place(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return hashed_elements.containsKey(position);
    }

    @Override
    public Object objectAt(Vector2d position){
        return hashed_elements.get(position);
    }

    @Override
    public String toString(){
        MapVisualizer mp = new MapVisualizer(this);
        return mp.draw(observer.getBottomLeft(), observer.getTopRight());
    }

    @Override
    public void moved(Vector2d oldPosition, Vector2d newPosition){
        AbstractWorldMapElement elem = hashed_elements.get(oldPosition);
        if(elem == null){
            return;
        }
        hashed_elements.remove(oldPosition);
        hashed_elements.put(newPosition, elem);
    }


    @Override
    public Vector2d  getTopRight(){
        return observer.getTopRight();
    }

    @Override
    public Vector2d  getBottomLeft(){
        return observer.getBottomLeft();
    }

}
