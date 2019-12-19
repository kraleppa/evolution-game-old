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

    public Animal(WorldMap map, Vector2D initialPosition, Integer[] genotype){
        this(map, initialPosition, genotype, map.startEnergy);
    }

    public Animal(WorldMap map, Vector2D initialPosition, Integer[] genotype, double startEnergy){
        this.orientation = Azimuth.N;
        this.position = map.betterPosition(initialPosition);
        this.map = map;
        this.genotype = genotype;
        this.energy = map.startEnergy;
        int rnd = new Random().nextInt(8);
        this.turnAround(rnd);
        this.energy = startEnergy;
    }

    static private Integer[] randomGenotype(){
        Random r = new Random();
        Integer[] genotype = new Integer[32];
        for (int i = 0; i < 32; i++){
            genotype[i] = r.nextInt(8);
        }

        int[] eliminatedGenes = new int[8];
        Arrays.fill(eliminatedGenes, 0);
        for (Integer gen : genotype){
            eliminatedGenes[gen]++;
        }

        int repeat = 0;
        for (int i = 0; i < 8; i++){
            if (eliminatedGenes[i] == 0){
                while(repeat < 32){                 //istnieje bardzo małe prawodopobieństwo że gen zniknie
                    int randomGene = r.nextInt(32);
                    if (eliminatedGenes[genotype[randomGene]] != 1){
                        genotype[randomGene] = i;
                        break;
                    }
                    repeat++;
                }

            }
        }

        Arrays.sort(genotype);

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

    public void addObserver(IPositionChangeObserver observer){
        observersSet.add(observer);
    }

    protected void removeObserver(IPositionChangeObserver observer){
        observersSet.remove(observer);
    }

    public Vector2D getPosition(){
        return this.position;
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
        return this.energy > (map.startEnergy / 2);
    }

    public Animal procreate(Animal other){
       Random random = new Random();
        List <Vector2D> emptyPositions = new ArrayList<>();
        for (Azimuth azimuth : Azimuth.values()){
            Vector2D newPosition = this.map.betterPosition(this.position.add(azimuth.toUnitVector()));
            if (this.map.objectAt(newPosition) == null){
                emptyPositions.add(newPosition);
            }
        }

        if (emptyPositions.isEmpty()) return null;

        Vector2D childPosition = emptyPositions.get(random.nextInt(emptyPositions.size()));


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
        int[] eliminatedGenes = new int[8];
        Arrays.fill(eliminatedGenes, 0);
        for (Integer gen : childGenotype){
            eliminatedGenes[gen]++;
        }
        int repeat = 0;
        for (int i = 0; i < 8; i++){
            if (eliminatedGenes[i] == 0){
                while(repeat < 32){                 //istnieje bardzo małe prawodopobieństwo że gen zniknie
                    int randomGene = random.nextInt(32);
                    if (eliminatedGenes[childGenotype[randomGene]] != 1){
                        childGenotype[randomGene] = i;
                        break;
                    }
                    repeat++;
                }

            }
        }

        Arrays.sort(childGenotype);

        Animal child = new Animal(this.map, childPosition, childGenotype, childEnergy);
        return child;
    }



}
