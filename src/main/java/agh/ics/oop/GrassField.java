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
            if(!this.isOccupied(new Vector2d(x, y))){
                elements.add(new Grass(new Vector2d(x, y)));
            }
        }
    }

    // metoda do testów
    public List<AbstractWorldMapElement> getElements(){
        return elements;
    }

    // metoda dla zadania dodatkowego
    @Override
    public boolean canMoveTo(Vector2d position) {
        for(AbstractWorldMapElement element : elements){
            if(element.isAt(position)){
                if(element.identifier() == 0){
                    elements.remove(element);
                    boolean flag = true;
                    while (flag) {
                        int x = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*grassCount)));
                        int y = ThreadLocalRandom.current().nextInt(0,  (int) Math.ceil(Math.sqrt(10*grassCount)));
                        if(!(this.isOccupied(new Vector2d(x, y)) && !(position.equals(new Vector2d(x, y))))){
                            elements.add(new Grass(new Vector2d(x, y)));
                            flag = false;
                        }
                    }
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    // aby na pewno dodać zwierze na miejsce trawy musimy nadpisac funkcje z absractworldmap
    @Override
    public boolean place(Animal animal) {
        for(AbstractWorldMapElement element : elements){
            if(element.isAt(animal.getPosition())){
                if(element.identifier() == 0){
                    elements.remove(element);
                    elements.add(animal);
                    animals_count ++;

                    boolean flag = true;
                    while (flag) {
                        int x = ThreadLocalRandom.current().nextInt(0, (int) Math.ceil(Math.sqrt(10 * grassCount)));
                        int y = ThreadLocalRandom.current().nextInt(0, (int) Math.ceil(Math.sqrt(10 * grassCount)));
                        if (!this.isOccupied(new Vector2d(x, y))) {
                            elements.add(new Grass(new Vector2d(x, y)));
                            flag = false;
                        }
                    }

                    return true;
                }
                return false;
            }
        }
        elements.add(animal);
        animals_count ++;
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
