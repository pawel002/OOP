package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    SortedSet<Vector2d> sortedX = new TreeSet<Vector2d>(new Vector2dCompareX());
    SortedSet<Vector2d> sortedY = new TreeSet<Vector2d>(new Vector2dCompareY());

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        sortedX.remove(oldPosition);
        sortedY.remove(oldPosition);
        sortedX.add(newPosition);
        sortedY.add(newPosition);
    }

    public static class Vector2dCompareX implements Comparator {
        @Override
        public int compare(Object a, Object b) {
            if(a == b)
                return 0;
            if(!(a instanceof Vector2d first) || !(b instanceof Vector2d second))
                return 0;
            if(first.equals(second))
                return 0;
            if(first.x == second.x)
                return (first.y < second.y ? -1:1);
            return (first.x < second.x ? -1:1);
        }
    }

    public static class Vector2dCompareY implements Comparator {
        @Override
        public int compare(Object a, Object b) {
            if(a == b)
                return 0;
            if(!(a instanceof Vector2d first) || !(b instanceof Vector2d second))
                return 0;
            if(first.equals(second))
                return 0;
            if(first.y == second.y)
                return (first.x < second.x ? -1:1);
            return (first.y < second.y ? -1:1);
        }
    }

    public void place(IMapElement elem){
        if(elem.identifier() == 1)
            ((Animal) elem).addObserver(this);
        Vector2d position = elem.getPosition();
        sortedX.add(position);
        sortedY.add(position);
    }

    public Vector2d getBottomLeft(){
        if(sortedX.isEmpty() || sortedY.isEmpty())
            return new Vector2d(0,0);
        return new Vector2d(sortedX.first().x, sortedY.first().y);
    }

    public Vector2d getTopRight() {
        if(sortedX.isEmpty() || sortedY.isEmpty())
            return new Vector2d(1,1);
        return new Vector2d(sortedX.last().x, sortedY.last().y);
    }
}
