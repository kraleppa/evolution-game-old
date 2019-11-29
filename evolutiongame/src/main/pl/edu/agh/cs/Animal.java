package pl.edu.agh.cs;

import java.util.EnumMap;
import java.util.Map;

public class Animal {
    private Vector2D position;
    private Azimuth orientation;
    private int energy;
    private boolean canProcreate;
    private String[] genotype;
    private Map<Azimuth, Double> directionProbability = new EnumMap<>(Azimuth.class);
    public Animal(String[] genotype, JSON json){
        this.genotype = genotype;
    }
}
