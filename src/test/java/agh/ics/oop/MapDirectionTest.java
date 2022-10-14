package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {
    @Test
    public void TestNextMethod(){
        MapDirection[] DirArr = {MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        for(MapDirection dir : DirArr){
            if(dir == MapDirection.NORTH)
                assertEquals(dir.next(), MapDirection.EAST);
            if(dir == MapDirection.EAST)
                assertEquals(dir.next(), MapDirection.SOUTH);
            if(dir == MapDirection.SOUTH)
                assertEquals(dir.next(), MapDirection.WEST);
            if(dir == MapDirection.WEST)
                assertEquals(dir.next(), MapDirection.NORTH);
        }
    }

    @Test
    public void TestPrevMethod(){
        MapDirection[] DirArr = {MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        for(MapDirection dir : DirArr){
            if(dir == MapDirection.NORTH)
                assertEquals(dir.previous(), MapDirection.WEST);
            if(dir == MapDirection.EAST)
                assertEquals(dir.previous(), MapDirection.NORTH);
            if(dir == MapDirection.SOUTH)
                assertEquals(dir.previous(), MapDirection.EAST);
            if(dir == MapDirection.WEST)
                assertEquals(dir.previous(), MapDirection.SOUTH);
        }
    }
}