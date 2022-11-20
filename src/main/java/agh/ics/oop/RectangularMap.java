package agh.ics.oop;

import java.util.ArrayList;
import java.util.Vector;

public class RectangularMap extends AbstractWorldMap{
    private final Vector2d bottomLeft;
    private final Vector2d topRight;

    public RectangularMap(int width, int height){
        bottomLeft = new Vector2d(0, 0);
        topRight = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        if(!(position.precedes(topRight) && position.follows(bottomLeft)))
            return false;
        return super.canMoveTo(position);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        if(pos.precedes(bottomLeft) || pos.follows(topRight))
            throw new IllegalArgumentException("Nie można dodać zwierzaka. Pole " + animal.getPosition().toString() + " jest już zajęte.");
        if (super.place(animal))
            return  true;
        throw new IllegalArgumentException("Nie można dodać zwierzaka. Pole " + animal.getPosition().toString() + " jest już zajęte.");
    }
}
