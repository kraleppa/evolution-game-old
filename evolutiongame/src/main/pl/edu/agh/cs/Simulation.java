package pl.edu.agh.cs;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.edu.agh.cs.visualization.RenderPanel;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;

public class Simulation {
    WorldMap map;
    int day;


    public Simulation(Vector2D upperRight){
        this.map = new WorldMap(upperRight);
        this.day = 0;
    }

    public Simulation(WorldMap map){
        this.map = map;
        this.day = 0;
    }
    public void nextDay(){
        map.clearDeadAnimals();
        map.turnAllAnimals();
        map.moveAllAnimals();
        map.eatAll();
        map.procreateAll();
        map.generateGrass();
        day++;
    }
    public void startSimulation(int numberOfDays) throws InterruptedException {
        System.out.println("Day: " + day);
        System.out.println(map.drawMap());
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RenderPanel panel = new RenderPanel(map, frame);
        frame.add(panel);
        frame.setVisible(true);
        java.util.concurrent.TimeUnit.SECONDS.sleep(1);

        for (int i = 1; i <= numberOfDays; i++){
            this.nextDay();
            day++;
            System.out.println("Day: " + day);
            System.out.println(map.drawMap());
            panel.repaint();
            java.util.concurrent.TimeUnit.SECONDS.sleep(1);
        }
    }



}
