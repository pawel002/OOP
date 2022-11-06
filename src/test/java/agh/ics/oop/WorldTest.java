package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    public void Animal_Test_Rotation(){
        IWorldMap map = new RectangularMap(3, 3);
        map.place(new Animal(map, new Vector2d(2, 2)));
        for(int i=0; i<4; i++){
            ((Animal) map.getAnimal(0)).move(MoveDirection.RIGHT);
        }
        assertEquals(MapDirection.NORTH, ((Animal) map.getAnimal(0)).getDirection());
    }

    @Test
    public void Animal_Test_Bounds(){
        // upper bound
        IWorldMap map = new RectangularMap(3, 3);
        map.place(new Animal(map, new Vector2d(2, 2)));
        for(int i=0; i<4; i++){
            ((Animal) map.getAnimal(0)).move(MoveDirection.FORWARD);
        }
        assertEquals(new Vector2d(2, 3), map.getAnimal(0).getPosition());

        // lower bound
        map.place(new Animal(map, new Vector2d(2, 2)));
        for(int i=0; i<4; i++){
            ((Animal) map.getAnimal(1)).move(MoveDirection.BACKWARD);
        }
        assertEquals(new Vector2d(2, 0), map.getAnimal(1).getPosition());

        // right bound
        map.place(new Animal(map, new Vector2d(2, 2)));
        ((Animal) map.getAnimal(2)).move(MoveDirection.RIGHT);
        for(int i=0; i<4; i++){
            ((Animal) map.getAnimal(2)).move(MoveDirection.FORWARD);
        }
        assertEquals(new Vector2d(3, 2), map.getAnimal(2).getPosition());

        // left bound
        map.place(new Animal(map, new Vector2d(2, 2)));
        ((Animal) map.getAnimal(3)).move(MoveDirection.RIGHT);
        for(int i=0; i<4; i++){
            ((Animal) map.getAnimal(3)).move(MoveDirection.BACKWARD);
        }
        assertEquals(new Vector2d(0, 2), map.getAnimal(3).getPosition());
    }

    // placing two animals on each other
    @Test
    public void Animal_Test_Place(){
        IWorldMap map = new RectangularMap(3, 3);
        map.place(new Animal(map, new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(2, 2)));
        assertEquals(1, map.animalCount());
    }

    // testing collision
    @Test
    public void Animal_Test_Collision(){
        IWorldMap map = new RectangularMap(3, 3);
        map.place(new Animal(map, new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(2, 1)));
        ((Animal) map.getAnimal(1)).move(MoveDirection.FORWARD);
        ((Animal) map.getAnimal(1)).move(MoveDirection.FORWARD);
        ((Animal) map.getAnimal(1)).move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 1), map.getAnimal(1).getPosition());
    }

    @Test
    public void Animal_Grass_Test_Collision(){
        IWorldMap map = new GrassField(10);
        map.getElements().add(new Grass(new Vector2d(2, 2)));
        map.place(new Animal(map, new Vector2d(2, 1)));
        ((Animal) map.getAnimal(0)).move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 2), map.getAnimal(0).getPosition());

        // sprawdzenie czy trawa została dodana
        int count = 0;
        for(AbstractWorldMapElement element : map.getElements()){
            if(element.identifier() == 0){
                count ++;
            }
        }
        assertEquals(11, count);
    }

}