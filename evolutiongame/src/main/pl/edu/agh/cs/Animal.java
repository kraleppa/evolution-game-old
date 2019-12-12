package pl.edu.agh.cs;

import java.util.*;

public class Animal {
    private Vector2D position;
    private Azimuth orientation;
    private int energy;
    private Integer[] genotype;
    WorldMap map;
    Set<IPositionChangeObserver> observersSet = new HashSet<>();

    public Animal (WorldMap map){
        this(map, new Vector2D(2,2));
    }

    public Animal (WorldMap map, Vector2D initialPosition){
        this.orientation = Azimuth.N;
        this.position = initialPosition;
        this.map = map;
    }

    public Animal(WorldMap map, Vector2D initialPosition, Integer[] Genotype){
        this.orientation = Azimuth.N;
        this.position = initialPosition;
        this.map = map;
        this.genotype = Genotype;
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

    public void turnAroundAuto(){
        int rnd = new Random().nextInt(this.genotype.length);
        this.turnAround(this.genotype[rnd]);
    }

    public void move(){
        Vector2D newVector = map.betterPosition(this.position.add(this.orientation.toUnitVector()));
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
