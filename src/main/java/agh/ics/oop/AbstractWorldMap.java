package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap {
    protected List<AbstractWorldMapElement> elements = new ArrayList<>();
    protected int animals_count;

    public boolean canMoveTo(Vector2d position) {
        for(AbstractWorldMapElement element : elements){
            if(element.isAt(position)){
                return false;
            }
        }
        return true;
    }

    public boolean place(Animal animal) {
        if(!this.isOccupied(animal.getPosition())){
            animals_count ++;
            elements.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position){
        for(AbstractWorldMapElement element : elements){
            if(element.isAt(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position){
        for(AbstractWorldMapElement element : elements){
            if(element.isAt(position)){
                return element;
            }
        }
        return null;
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
}
