package agh.ics.oop;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected List<AbstractWorldMapElement> elements = new ArrayList<>();
    protected Map<Vector2d, AbstractWorldMapElement> hashed_elements = new HashMap<>();
    protected int animals_count;

    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    public boolean place(Animal animal) {
        if(!this.isOccupied(animal.getPosition())){
            animal.addObserver(this);
            hashed_elements.put(animal.getPosition(), animal);
            animals_count ++;
            elements.add(animal);
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
    public AbstractWorldMapElement getAnimal(int i){
        i = i % animals_count;
        int j = 0;
        for(AbstractWorldMapElement element : elements) {
            if(element.identifier() == 1){
                if(j == i){
                    return element;
                }
                j ++;
            }
        }
        return null;
    }

    @Override
    public List<AbstractWorldMapElement> getElements(){
        return elements;
    }

    @Override
    public int animalCount(){
        return animals_count;
    }

    @Override
    public String toString(){
        MapVisualizer mp = new MapVisualizer(this);
        return mp.draw(this.getLowerLeft(), this.getUpperRight());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        AbstractWorldMapElement elem = hashed_elements.get(oldPosition);
        if(elem == null){
            return;
        }
        hashed_elements.remove(oldPosition);
        hashed_elements.put(newPosition, elem);
    }
}
