package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest{
    @Test
    public void Vector2D_toString_Test(){
        for(int i=0; i<10; i++){
            int x = ThreadLocalRandom.current().nextInt(-100, 100);
            int y = ThreadLocalRandom.current().nextInt(-100, 100);
            assertEquals(String.join("", "(", String.valueOf(x), ", ", String.valueOf(y), ")"), new Vector2d(x, y).toString());
        }
    }

    @Test
    public void Vector2D_precedes_Test(){
        Vector2d A = new Vector2d(10, 100);
        Vector2d B = new Vector2d(15, 150);
        assertTrue(A.precedes(B));
        A = new Vector2d(20, 160);
        assertFalse(A.precedes(B));
        A = new Vector2d(15, 150);
        assertTrue(A.precedes(B));
    }

    @Test
    public void Vector2D_follows_Test(){
        Vector2d A = new Vector2d(10, 100);
        Vector2d B = new Vector2d(15, 150);
        assertFalse(A.follows(B));
        A = new Vector2d(20, 160);
        assertTrue(A.follows(B));
        A = new Vector2d(15, 150);
        assertTrue(A.follows(B));
    }

    @Test
    public void Vector2D_equals_Test(){
        Vector2d A = new Vector2d(10, 15);
        assertTrue(A.equals(A));
        assertFalse(A.equals(5));
        Vector2d B = new Vector2d(11, 15);
        assertFalse(A.equals(B));
        Vector2d C = new Vector2d(10, 15);
        assertTrue(A.equals(C));
    }

    @Test
    public void Vector2D_upperRight_Test(){
        Vector2d A = new Vector2d(10, 200);
        Vector2d B = new Vector2d(15, 150);
        assertTrue(A.upperRight(B).equals(new Vector2d(15, 200)));
        A = new Vector2d(20, 100);
        assertTrue(A.upperRight(B).equals(new Vector2d(20, 150)));
        A = new Vector2d(15, 150);
        assertTrue(A.upperRight(B).equals(A));
    }

    @Test
    public void Vector2D_lowerLeft_Test() {
        Vector2d A = new Vector2d(10, 200);
        Vector2d B = new Vector2d(15, 150);
        assertTrue(A.lowerLeft(B).equals(new Vector2d(10, 150)));
        A = new Vector2d(20, 100);
        assertTrue(A.lowerLeft(B).equals(new Vector2d(15, 100)));
        A = new Vector2d(15, 150);
        assertTrue(A.lowerLeft(B).equals(new Vector2d(15, 150)));
    }

    @Test
    public void Vector2D_add_Test() {
        for(int i=0; i<10; i++){
            int[] Arr = new int[4];
            for(int j=0; j<4; j++){
                Arr[j] = ThreadLocalRandom.current().nextInt(-100, 100);
            }
            Vector2d A = new Vector2d(Arr[0], Arr[1]);
            Vector2d B = new Vector2d(Arr[2], Arr[3]);
            Vector2d C = new Vector2d(Arr[0] + Arr[2], Arr[1] + Arr[3]);
            assertTrue(A.add(B).equals(C));
        }
    }

    @Test
    public void Vector2D_subtract_Test() {
        for(int i=0; i<10; i++){
            int[] Arr = new int[4];
            for(int j=0; j<4; j++){
                Arr[j] = ThreadLocalRandom.current().nextInt(-100, 100);
            }
            Vector2d A = new Vector2d(Arr[0], Arr[1]);
            Vector2d B = new Vector2d(Arr[2], Arr[3]);
            Vector2d C = new Vector2d(Arr[0] - Arr[2], Arr[1] - Arr[3]);
            assertTrue(A.subtract(B).equals(C));
        }
    }

    @Test
    public void Vector2D_opposite_Test() {
        for(int i=0; i<10; i++){
            int[] Arr = new int[2];
            for(int j=0; j<2; j++){
                Arr[j] = ThreadLocalRandom.current().nextInt(-100, 100);
            }
            Vector2d A = new Vector2d(Arr[0], Arr[1]);
            Vector2d B = new Vector2d(-Arr[0], -Arr[1]);
            assertTrue(A.opposite().equals(B));
        }
    }
}