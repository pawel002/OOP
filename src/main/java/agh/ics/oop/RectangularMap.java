package agh.ics.oop;

import java.util.ArrayList;

class RectangularMap implements IWorldMap{
    public Vector2d LowerLeft;
    public Vector2d UpperRight;
    public ArrayList<Animal> animals = new ArrayList<>();

    RectangularMap(int width, int height){
        LowerLeft = new Vector2d(0, 0);
        UpperRight = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        if(!(position.precedes(UpperRight) && position.follows(LowerLeft)))
            return false;
        for(Animal animal : animals){
            if(animal.isAt(position))
                return false;
        }
        return true;
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        if(pos.precedes(LowerLeft) || pos.follows(UpperRight)) return false;
        for(Animal ani : animals){
            if(ani.isAt(pos)) return false;
        }
        animals.add(animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Animal animal : animals){
            if(animal.isAt(position)) return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for(Animal animal : animals){
            if(animal.isAt(position)) return animal;
        }
        return null;
    }

    // helper functions that return i-th animal modulo animals.len()
    @Override
    public Animal getAnimal(int i){
        return animals.get(i % animals.size());
    }

    public String toString(){
        MapVisualizer mp = new MapVisualizer(this);
        return mp.draw(LowerLeft, UpperRight);
    }

    // helper function that returns number of animals
    @Override
    public int animalCount(){
        return animals.size();
    }

    @Override
    public int[] getSize(){
        return new int[]{UpperRight.x , UpperRight.y};
    }
}
