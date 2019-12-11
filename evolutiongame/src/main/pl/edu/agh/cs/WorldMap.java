package pl.edu.agh.cs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorldMap implements IWorldMap {
    protected List<Animal> animalsList = new ArrayList<>();
    protected Map<Vector2D, Animal> animalsMap = new LinkedHashMap<>();
    protected Vector2D loweLeft;
    protected Vector2D upperRight;

    @Override
    public boolean canMoveTo(Vector2D position) {
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
        return animal
    }
}
