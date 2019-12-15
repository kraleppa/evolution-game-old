package pl.edu.agh.cs;

import javax.swing.*;
import java.util.*;

public class Animal {
    private Vector2D position;
    private Azimuth orientation;
    private double energy;
    public final Integer[] genotype;
    WorldMap map;
    Set<IPositionChangeObserver> observersSet = new HashSet<>();

    public Animal (WorldMap map){
        this(map, new Vector2D(2,2));
    }

    public Animal (WorldMap map, Vector2D initialPosition){
        this(map, initialPosition, Animal.randomGenotype());
    }

    public Animal(WorldMap map, Vector2D initialPosition, Integer[] Genotype){

        this.orientation = Azimuth.N;
        this.position = map.betterPosition(initialPosition);
        this.map = map;
        this.genotype = Genotype;
        this.energy = map.startEnergy;
    }

    static private Integer[] randomGenotype(){
        Random r = new Random();
        Integer[] genotype = new Integer[32];
        for (int i = 0; i < 32; i++){
            genotype[i] = r.nextInt(8);
        }
        return genotype;
    }

    public String toString(){
        switch(this.orientation){
            case N: return "↑";
            case NE: return "↗";
            case E: return "→";
            case SE: return "↘";
            case S: return "↓";
            case SW: return "↙";
            case W:  return "←";
            case NW: return "↖";
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
            this.energy -= map.moveEnergy;
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

    public boolean isDead(){
        return (energy <= 0);
    }

    public double getEnergy() {
        return energy;
    }

    public void eat(double addEnergy){
        this.energy += addEnergy;
    }

    public boolean canProcreate(){
        return this.energy > (map.startEnergy / 4);
    }

    public Animal procreate(Animal other){
        //position
        Random random = new Random();
        int[] table = new int[]{-1,1};
        int childX = this.position.x + table[random.nextInt(2)];
        int childY = this.position.y + table[random.nextInt(2)];
        Vector2D childPosition = map.betterPosition(new Vector2D(childX, childY));

        //energy
        double childEnergy = this.energy / 4 + other.energy / 4;

        //genotype
        Integer[] childGenotype = new Integer[32];
        for (int i = 0; i < 11; i++){
            childGenotype[i] = this.genotype[i];
        }

        for (int i = 11; i < 21; i++){
            childGenotype[i] = other.genotype[i];
        }

        for (int i = 21; i < 32; i++){
            childGenotype[i] = this.genotype[i];
        }


        Animal child = new Animal(this.map, childPosition, childGenotype);

        //direction
        child.turnAround(random.nextInt(8));
        return child;
    }



}
