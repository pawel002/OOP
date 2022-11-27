package agh.ics.oop;

import java.io.FileNotFoundException;

/**
 * The interface responsible for managing the moves of the animals.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IEngine {
    /**
     * Move the animal on the map according to the provided move directions. Every
     * n-th direction should be sent to the n-th animal on the map.
     *
     */
    void run(boolean visualize) throws FileNotFoundException;

    Animal getAnimal(int i);

    void setDirections(MoveDirection[] directions);

//    void visualize() throws InterruptedException;
}