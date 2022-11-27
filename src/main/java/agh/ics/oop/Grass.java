package agh.ics.oop;

public class Grass extends AbstractWorldMapElement {

    public Grass(Vector2d v){
        position = v;
    }

    public String toString() {
        return "*";
    }

    @Override
    public int identifier(){
        return 0;
    }

    public String getResName(){
        return "src/main/resources/grass.png";
    }
}
