package agh.ics.oop;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrassField extends AbstractWorldMap{
    public int grassCount;

    public GrassField(int n) {
        grassCount = n;
        while (elements.size() != grassCount) {
            int x = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*n)));
            int y = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*n)));
            Vector2d pos = new Vector2d(x, y);
            if(!this.isOccupied(pos)){
                Grass grass = new Grass(pos);
                elements.add(grass);
                hashed_elements.put(pos, grass);
            }
        }
    }

    // metoda do testów
    public List<AbstractWorldMapElement> getElements(){
        return elements;
    }

    // metoda dla zadania dodatkowego
    @Override
    public boolean canMoveTo(Vector2d position){
        AbstractWorldMapElement elem = hashed_elements.get(position);
        if(elem == null){
            return true;
        }
        // animal then we cant move
        if(elem.identifier() == 1){
            return false;
        }
        // grass so we can move
        elements.remove(elem);
        boolean flag = true;
        while (flag) {
            int x = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*grassCount)));
            int y = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*grassCount)));
            Vector2d pos = new Vector2d(x, y);
            if((!this.isOccupied(pos)) && (!pos.equals(position))){
                Grass grass = new Grass(pos);
                elements.add(grass);
                hashed_elements.put(pos, grass);
                flag = false;
            }
        }
        return true;
    }

    // aby na pewno dodać zwierze na miejsce trawy musimy nadpisac funkcje z absractworldmap
    @Override
    public boolean place(Animal animal){
        AbstractWorldMapElement elem = hashed_elements.get(animal.getPosition());
        if(elem == null){
            animal.addObserver(this);
            elements.add(animal);
            hashed_elements.put(animal.getPosition(), animal);
            animals_count ++;
            return true;
        }
        // if animal we cant place
        if(elem.identifier() == 1){
            return false;
        }

        // else its grass
        animal.addObserver(this);
        elements.remove(elem);
        elements.add(animal);
        hashed_elements.put(animal.getPosition(), animal);

        boolean flag = true;
        while (flag) {
            int x = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*grassCount)));
            int y = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*grassCount)));
            Vector2d pos = new Vector2d(x, y);
            if(!this.isOccupied(pos)){
                Grass grass = new Grass(pos);
                elements.add(grass);
                hashed_elements.put(pos, grass);
                flag = false;
            }
        }

        return true;
    }

    @Override
    public Vector2d getLowerLeft(){
        int min_x = elements.get(0).getPosition().x;
        int min_y = elements.get(0).getPosition().y;
        for(AbstractWorldMapElement element : elements){
            min_x = Math.min(min_x, element.getPosition().x);
            min_y = Math.min(min_y, element.getPosition().y);
        }
        return new Vector2d(min_x, min_y);
    }

    @Override
    public Vector2d getUpperRight(){
        int max_x = elements.get(0).getPosition().x;
        int max_y = elements.get(0).getPosition().y;
        for(AbstractWorldMapElement element : elements){
            max_x = Math.max(max_x, element.getPosition().x);
            max_y = Math.max(max_y, element.getPosition().y);
        }
        return new Vector2d(max_x, max_y);
    }

    @Override
    public int[] getSize() {
        return new int[0];
    }
}
