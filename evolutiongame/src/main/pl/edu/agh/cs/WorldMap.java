package pl.edu.agh.cs;

import java.util.*;

public class WorldMap implements IWorldMap{
    private List<Animal> animalsList = new ArrayList<>();
    private Map<Vector2D, Animal> animalsMap = new HashMap<>();
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
        animalsList.add(animal);
        animalsMap.put(animal.getPosition(), animal);
        return true;
    }

    @Override
    public void run(Azimuth[] directions){
        ;
    }

    @Override
    public boolean isOccupied(Vector2D position) {
        if (objectAt(position) != null)
            return true;
        return false;
    }

    @Override
    public Object objectAt(Vector2D position){
        return animalsMap.get(position);
    }

    public String drawMap(){
        return visualizer.draw(this.lowerLeft, this.upperRight);
    }


}
