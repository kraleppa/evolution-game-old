package pl.edu.agh.cs;

import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver{
    private List<Animal> animalsList = new ArrayList<>();
    private Map<Vector2D, HashSet<Animal>> animalsMap = new HashMap<>();
    private Map<Vector2D, Grass> grassMap = new HashMap<>();
    private Vector2D lowerLeft;
    private Vector2D upperRight;
    MapVisualizer visualizer;
    int width;
    int height;
    int dayNumber;

    public WorldMap(Vector2D upperRight){
        this.upperRight = upperRight;
        this.lowerLeft = new Vector2D(0,0);
        this.visualizer = new MapVisualizer(this);
        this.height = upperRight.y + 1;
        this.width = upperRight.x + 1;
        this.dayNumber = 0;
    }

    public void nextDay(){
        for (Animal animal : animalsList){
            animal.turnAroundAuto();
        }

        for (Animal animal : animalsList){
            animal.move();
        }
    }

    @Override
    public boolean canMoveTo(Vector2D position){
        return true;
    }

    public Vector2D betterPosition(Vector2D position){
        int nx;
        int ny;

        if (position.x < lowerLeft.x){
            nx = (width - Math.abs(position.x % width)) % width;
        }
        else{
            nx = Math.abs(position.x % width);
        }

        if (position.y < lowerLeft.y){
            ny = (height - Math.abs(position.y % height)) % height;
        }
        else{
            ny = Math.abs(position.y % height);
        }
        return new Vector2D(nx, ny);
    }

    @Override
    public boolean place(Animal animal){
        Vector2D animalPosition = animal.getPosition();
        HashSet<Animal> tmp = animalsMap.get(animalPosition);
        if (tmp == null){
            tmp = new HashSet<>();
            tmp.add(animal);
            animalsMap.put(animalPosition, tmp);
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
        if (set == null || set.isEmpty())
            return this.grassMap.get(position);
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

    public void createGrass(Vector2D position){
        Grass grass = new Grass(this.betterPosition(position));
        grassMap.put(this.betterPosition(position), grass);
    }

}
