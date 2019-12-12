package pl.edu.agh.cs;

import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver{
    private List<Animal> animalsList = new ArrayList<>();
    private Map<Vector2D, HashSet<Animal>> animalsMap = new HashMap<>();
    private Vector2D lowerLeft;
    private Vector2D upperRight;
    MapVisualizer visualizer;


    public WorldMap(Vector2D upperRight){
        this.upperRight = upperRight;
        this.lowerLeft = new Vector2D(0,0);
        this.visualizer = new MapVisualizer(this);
    }
    @Override
    public boolean canMoveTo(Vector2D position){
        return true;
    }

    @Override
    public boolean place(Animal animal){
        HashSet<Animal> tmp = animalsMap.get(animal.getPosition());
        if (tmp == null){
            tmp = new HashSet<>();
            tmp.add(animal);
            animalsMap.put(animal.getPosition(), tmp);
        }
        else{
            tmp.add(animal);
        }
        animalsList.add(animal);
        animal.addObserver(this);
        return true;
    }

    @Override
    public void run(Integer directions[]){
        int i = 0;
        for (Integer direction : directions){
            Animal tmp = animalsList.get(i % animalsList.size());
            tmp.turnAround(direction);
            tmp.move();
            i++;
        }

    }

    @Override
    public boolean isOccupied(Vector2D position) {
        if (objectAt(position) != null)
            return true;
        return false;
    }

    @Override
    public Object objectAt(Vector2D position){
        HashSet<Animal> set = animalsMap.get(position);
        if (set == null)
            return null;
        if (set.isEmpty())
            return null;
        if (set.size() == 1){
            for (Animal animal : set){
                return animal;
            }
        }
        return set;
    }

    public String drawMap(){
        return visualizer.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public void positionChanged(Vector2D oldPosition, Vector2D newPosition, Animal animal) {
        animalsMap.get(oldPosition).remove(animal);
        HashSet<Animal> tmp = animalsMap.get(newPosition);
        if (tmp == null){
            tmp = new HashSet<>();
            tmp.add(animal);
            animalsMap.put(newPosition, tmp);
        }
        else{
            tmp.add(animal);
        }
    }

}
