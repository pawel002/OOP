package agh.ics.oop;

public interface IPositionChangeObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition);

    void place(IMapElement elem);
    Vector2d getBottomLeft();

    Vector2d getTopRight();
}
