package pl.edu.agh.cs;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Animal {
    private Vector2D position;
    private Azimuth orientation;
    private int energy;
    private String[] genotype;
    IWorldMap map;
    Set<IPositionChangeObserver> observersSet = new HashSet<>();

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

    public void move(){
        Vector2D newVector = this.position.add(this.orientation.toUnitVector());
        if (map.canMoveTo(newVector)){
            positionChanged(this.getPosition(), newVector);
            this.position = newVector;
        }
    }

    protected void addObserver(IPositionChangeObserver observer){
        observersSet.add(observer);
    }

    protected void removeObserver(IPositionChangeObserver observer){
        observersSet.remove(observer);
    }

    public Vector2D getPosition(){
        return this.position;
    }

    public Azimuth getOrientation(){
        return this.orientation;
    }

    private void positionChanged(Vector2D oldPosition, Vector2D newPosition) {
        for (IPositionChangeObserver observer : observersSet) {
            observer.positionChanged(oldPosition, newPosition, this);
        }
    }

}
