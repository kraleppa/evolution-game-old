package pl.edu.agh.cs;

import java.util.*;

public class WorldMap implements IWorldMap {
    private List<Animal> animalsList = new ArrayList<>();
    private Map<Vector2D, LinkedList<Animal>> animalsMap = new HashMap<>();
    private Map<Vector2D, Grass> grassMap = new HashMap<>();
    private Vector2D lowerLeft;
    private Vector2D upperRight;
    private Vector2D jungleLowerLeft;
    private Vector2D jungleUpperRight;


    public Vector2D positionCalculator(Vector2D position){

        int nx;
        int ny;
        if (position.getX() < lowerLeft.getX()){
            nx = upperRight.getX();
        }
        else if (position.getX() > upperRight.getX()){
            nx = lowerLeft.getX();
        }
        else {
            nx = position.getX();
        }

        if (position.getY() < lowerLeft.getY()){
            ny = upperRight.getY();
        }
        else if (position.getY() > upperRight.getY()){
            ny = lowerLeft.getY();
        }
        else {
            ny = position.getY();
        }

        return new Vector2D(nx, ny);

    }

    @Override
    public boolean canMoveTo(Vector2D position) {
        Vector2D betterPosition = positionCalculator(position);
        if (animalsMap.get(betterPosition) == null) return true;
        if (animalsMap.get(betterPosition).size() < 2) return true;
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public void run(Azimuth[] directions) {

    }

    @Override
    public boolean isOccupied(Vector2D position) {
        return false;
    }

    @Override
    public Object objectAt(Vector2D position) {
        if (!animalsMap.containsKey(position)){
            return grassMap.get(position);
        }
        else{
            return animalsMap.get(position);
        }

    }
}
