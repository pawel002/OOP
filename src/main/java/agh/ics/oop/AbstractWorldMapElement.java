package agh.ics.oop;

abstract class AbstractWorldMapElement implements IMapElement{
    protected Vector2d position;

    @Override
    public Vector2d getPosition(){
        return position;
    }

    @Override
    public boolean isAt(Vector2d pos) {
        return position.equals(pos);
    }

    @Override
    public int identifier(){
        return 2;
    }
}
