package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {

    @Test
    public void Animal_Test_Reset(){
        Animal animal = new Animal();
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.RIGHT);
        animal.reset();
        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getDirection());
    }

    @Test
    public void Animal_Test_Bounds(){
        String[] testString1 = {"f", "f", "f", "f"};
        Animal animal1 = new Animal();
        for(MoveDirection arg : OptionsParser.parse(testString1)){
            animal1.move(arg);
        }
        assertEquals(new Vector2d(2, 4), animal1.getPosition());
        assertEquals(MapDirection.NORTH, animal1.getDirection());

        String[] testString2 = {"r", "f", "f", "f"};
        animal1.reset();
        for(MoveDirection arg : OptionsParser.parse(testString2)){
            animal1.move(arg);
        }
        assertEquals(new Vector2d(4, 2), animal1.getPosition());
        assertEquals(MapDirection.EAST, animal1.getDirection());

        String[] testString3 = {"b", "b", "b", "b"};
        animal1.reset();
        for(MoveDirection arg : OptionsParser.parse(testString3)){
            animal1.move(arg);
        }
        assertEquals(new Vector2d(2, 0), animal1.getPosition());
        assertEquals(MapDirection.NORTH, animal1.getDirection());

        String[] testString4 = {"r", "b", "b", "b"};
        animal1.reset();
        for(MoveDirection arg : OptionsParser.parse(testString4)){
            animal1.move(arg);
        }
        assertEquals(new Vector2d(0, 2), animal1.getPosition());
        assertEquals(MapDirection.EAST, animal1.getDirection());
    }

    @Test
    public void Animal_Test_Rotation(){
        String[] testString1 = {"r", "r", "r", "r"};
        Animal animal = new Animal();
        for(MoveDirection arg : OptionsParser.parse(testString1)){
            animal.move(arg);
        }
        assertEquals(MapDirection.NORTH, animal.getDirection());
    }

    @Test
    public void Parser_Test(){
        String[] testString1 = {"r", "x", "right", "l", "left", "fasnoj", "asd", "f"};
        assertArrayEquals(OptionsParser.parse(testString1), new MoveDirection[]{MoveDirection.RIGHT,  MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.FORWARD});
    }
}