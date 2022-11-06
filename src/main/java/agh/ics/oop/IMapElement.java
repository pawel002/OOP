package agh.ics.oop;

public interface IMapElement {

    public boolean isAt(Vector2d pos);

    public Vector2d getPosition();

    // returns 1 for animal and 0 for grass
    public int identifier();
}
