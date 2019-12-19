package pl.edu.agh.cs;

import pl.edu.agh.cs.visualization.RenderPanel;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7
//0, 0, 0, 0, 3, 3, 3, 0, 1, 1, 4, 4, 4, 4, 4, 2, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 0, 0, 1, 1
public class World {
    public static void main(String[] args) throws InterruptedException {
        Simulation sim = new Simulation(1000, 6,  20);
        sim.startSimulation();
    }

}
