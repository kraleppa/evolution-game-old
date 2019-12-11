package pl.edu.agh.cs;

import java.util.EnumMap;
import java.util.Map;

public class Animal {
    private Vector2D position;
    private Azimuth orientation;
    private int energy;
    private String[] genotype;
    IWorldMap map;
    public Animal (IWorldMap map){
        this.orientation = Azimuth.N;
        this.position = new Vector2D(2,2);
        this.map = map;
    }

    public Animal (IWorldMap map, Vector2D initialPosition){
        this.orientation = Azimuth.N;
        this.position = initialPosition;
        this.map = map;
    }

    public String toString(){
        switch(this.orientation){
            case N: return "⭡";
            case NE: return "⭧";
            case E: return "⭢";
            case SE: return "⭨";
            case S: return "⭣";
            case SW: return "⭩";
            case W:  return "⭠";
            case NW: return "⭦";
            default: return null;
        }
    }

    public void turnAround(int number){
        for (int i = 0; i < number; i++){
            this.orientation = this.orientation.next();
        }
    }

    public void move(Azimuth direction){
        //TODO
    }

    public Vector2D getPosition(){
        return this.position;
    }

    public Azimuth getOrientation(){
        return this.orientation;
    }


}
