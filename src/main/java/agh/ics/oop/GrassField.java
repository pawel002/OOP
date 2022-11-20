package agh.ics.oop;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GrassField extends AbstractWorldMap{
    public int grassCount;

    public GrassField(int n) {
        grassCount = n;
        while (hashed_elements.size() != grassCount) {
            int x = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*n)));
            int y = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*n)));
            Vector2d pos = new Vector2d(x, y);
            if(!this.isOccupied(pos)){
                Grass grass = new Grass(pos);
                observer.place(grass);
                hashed_elements.put(pos, grass);
            }
        }
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
        boolean flag = true;
        while (flag) {
            int x = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*grassCount)));
            int y = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*grassCount)));
            Vector2d pos = new Vector2d(x, y);
            if((!this.isOccupied(pos)) && (!pos.equals(position))){
                Grass grass = new Grass(pos);
                observer.positionChanged(position, pos);
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
            hashed_elements.put(animal.getPosition(), animal);
            observer.place(animal);
            return true;
        }
        // if animal we cant place
        if(elem.identifier() == 1){
            throw new IllegalArgumentException("Nie można dodać zwierzaka. Pole " + animal.getPosition().toString() + " jest już zajęte.");
        }

        // else its grass
        hashed_elements.remove(animal.getPosition());
        hashed_elements.put(animal.getPosition(), animal);
        observer.place(animal);

        boolean flag = true;
        while (flag) {
            int x = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*grassCount)));
            int y = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*grassCount)));
            Vector2d pos = new Vector2d(x, y);
            if(!this.isOccupied(pos)){
                Grass grass = new Grass(pos);
                observer.positionChanged(animal.getPosition(), pos);
                hashed_elements.put(pos, grass);
                flag = false;
            }
        }

        return true;
    }

}
