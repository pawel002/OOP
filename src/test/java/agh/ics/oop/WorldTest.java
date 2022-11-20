package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    public void Parser_Test_Exception(){
        try {
            MoveDirection[] directions = OptionsParser.parse(List.of(new String[]{"x"}));
        } catch (IllegalArgumentException exception){
            assertEquals("x is not legal move specification",  exception.getMessage());
        }
    }

    @Test
    public void Animal_Test_Rotation(){
        IWorldMap map = new RectangularMap(3, 3);
        Vector2d[] positions = { new Vector2d(2,2)};
        MoveDirection[] directions = OptionsParser.parse(List.of(new String[]{"r", "r", "r", "r"}));
        SimulationEngine sim  =  new SimulationEngine(directions, map,  positions);
        sim.run(false);
        assertEquals(MapDirection.NORTH,  sim.getAnimal(0).getDirection());
    }

    @Test
    public void Animal_Test_Bounds1() {
        // upper bound
        IWorldMap map = new RectangularMap(3, 3);
        Vector2d[] positions = {new Vector2d(2, 2)};
        MoveDirection[] directions = OptionsParser.parse(List.of(new String[]{"f", "f", "f", "f"}));
        SimulationEngine sim = new SimulationEngine(directions, map, positions);
        sim.run(false);
        assertEquals(new Vector2d(2, 3), sim.getAnimal(0).getPosition());
    }

    @Test
    public void Animal_Test_Bounds2() {
        // lower bound
        IWorldMap map = new RectangularMap(3, 3);
        Vector2d[] positions = {new Vector2d(2, 2)};
        MoveDirection[] directions = OptionsParser.parse(List.of(new String[]{"b", "b", "b", "b"}));
        SimulationEngine sim = new SimulationEngine(directions, map, positions);
        sim.run(false);
        assertEquals(new Vector2d(2, 0), sim.getAnimal(0).getPosition());
    }

    @Test
    public void Animal_Test_Bounds3() {
        // right bound
        IWorldMap map = new RectangularMap(3, 3);
        Vector2d[] positions = {new Vector2d(2, 2)};
        MoveDirection[] directions = OptionsParser.parse(List.of(new String[]{"r", "f", "f", "f", "f"}));
        SimulationEngine sim = new SimulationEngine(directions, map, positions);
        sim.run(false);
        assertEquals(new Vector2d(3, 2), sim.getAnimal(0).getPosition());
    }

    @Test
    public void Animal_Test_Bounds4() {
        // left bound
        IWorldMap map = new RectangularMap(3, 3);
        Vector2d[] positions = {new Vector2d(2, 2)};
        MoveDirection[] directions = OptionsParser.parse(List.of(new String[]{"l", "f", "f", "f", "f"}));
        SimulationEngine sim = new SimulationEngine(directions, map, positions);
        sim.run(false);
        assertEquals(new Vector2d(0, 2), sim.getAnimal(0).getPosition());
    }


    // placing two animals on each other
    @Test
    public void Animal_Test_Place(){
        try {
            IWorldMap map = new RectangularMap(3, 3);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(2, 2)};
            MoveDirection[] directions = OptionsParser.parse(List.of(new String[]{}));
            SimulationEngine sim = new SimulationEngine(directions, map, positions);
            sim.run(false);
            assertEquals(1, sim.animal_count);
        }  catch (IllegalArgumentException  exception){
            assertEquals("Nie można dodać zwierzaka. Pole (2, 2) jest już zajęte.", exception.getMessage());
        }
    }

    // testing collision
    @Test
    public void Animal_Test_Collision(){
        IWorldMap map = new RectangularMap(3, 3);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(2, 1)};
        MoveDirection[] directions = OptionsParser.parse(List.of(new String[]{"r", "f", "r", "f", "r", "f"}));
        SimulationEngine sim = new SimulationEngine(directions, map, positions);
        sim.run(false);
        assertEquals(new Vector2d(2, 1), sim.getAnimal(1).getPosition());
    }

    @Test
    public void Animal_Grass_Test_Collision(){

        IWorldMap map = new GrassField(1);
        MoveDirection[] directions = OptionsParser.parse(List.of(new String[]{"f"}));
        //  find grass
        Vector2d pos =  new Vector2d(0,0);
        for (int i=0; i<4;  i++){
            for (int j=0; j<4;  j++){
                pos = new Vector2d(i, j);
                AbstractWorldMapElement elem = (AbstractWorldMapElement) map.objectAt(pos);
                if (elem == null)
                    continue;
                if (elem.identifier() ==  0) {
                    break;
                }
            }
        }
        Vector2d[] positions = {new Vector2d(pos.x, pos.y-1)};
        SimulationEngine sim = new SimulationEngine(directions, map, positions);
        sim.run(false);
        // find added grass
        int  count = 0;
        for (int i=0; i<4;  i++){
            for (int j=0; j<4;  j++){
                pos = new Vector2d(i, j);
                AbstractWorldMapElement elem = (AbstractWorldMapElement) map.objectAt(pos);
                if (elem == null)
                    continue;
                if (elem.identifier() ==  0) {
                    count ++;
                }
            }
        }
        assertEquals(1, count);
    }



    @Test
    public void GrassField_Expansion_Test(){
        IWorldMap map = new GrassField(10);
        Animal dog = new Animal(map, new Vector2d(11, 11));
        map.place(dog);
        assertEquals(new Vector2d(11, 11), map.getTopRight());
        dog.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(11, 12), map.getTopRight());
    }

    @Test
    public void Hashing_Test(){
        IWorldMap map = new RectangularMap(4, 4);
        Animal dog = new Animal(map, new Vector2d(2, 2));
        Animal cat = new Animal(map, new Vector2d(2, 3));
        map.place(dog);
        map.place(cat);
        cat.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);
        // jezeli obserwer nie dzialalby prawodlowo, to ruch psa do gory powinien byc niemozliwy
        // ponieważ w hashmapie dalej pozostawałaby stara pozycja kota
        assertEquals(new Vector2d(2, 3), dog.getPosition());
    }
}