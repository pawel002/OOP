package agh.ics.oop;

import java.util.ArrayList;
import java.util.Vector;

class RectangularMap extends AbstractWorldMap{
    public Vector2d LowerLeft;
    public Vector2d UpperRight;

    RectangularMap(int width, int height){
        LowerLeft = new Vector2d(0, 0);
        UpperRight = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        if(!(position.precedes(UpperRight) && position.follows(LowerLeft)))
            return false;
        return super.canMoveTo(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        if(pos.precedes(LowerLeft) || pos.follows(UpperRight)) return false;
        return super.place(animal);
    }

    @Override
    public Vector2d getLowerLeft(){
        return LowerLeft;
    }

    @Override
    public Vector2d getUpperRight(){
        return UpperRight;
    }

    @Override
    public int[] getSize(){
        return new int[]{UpperRight.x , UpperRight.y};
    }
}
