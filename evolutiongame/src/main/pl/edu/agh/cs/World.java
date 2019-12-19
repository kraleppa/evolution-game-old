package pl.edu.agh.cs;

import pl.edu.agh.cs.visualization.RenderPanel;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class World {
    public static void main(String[] args) throws InterruptedException {
        Simulation sim = new Simulation(1000, 30,  30);
        sim.startSimulation();
    }

}
