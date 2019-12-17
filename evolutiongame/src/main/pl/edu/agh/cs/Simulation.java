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
    int numberOfAnimals = 6;

    public Simulation(Vector2D upperRight){
        this.map = new WorldMap(upperRight);
        this.day = 0;
    }

    public Simulation(WorldMap map){
        this.map = map;
        this.day = 0;
    }
    public void nextDay(){
        this.numberOfAnimals -= map.clearDeadAnimals();
        map.turnAllAnimals();
        map.moveAllAnimals();
        map.eatAll();
        this.numberOfAnimals += map.procreateAll();
        map.generateGrass();
        day++;
    }
    public void startSimulation(int numberOfDays) throws InterruptedException {
        System.out.println("Day: " + day);
        System.out.println(map.drawMap());
        JFrame frame = new JFrame();
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RenderPanel panel = new RenderPanel(map, frame);
        frame.add(panel);
        frame.setVisible(true);
        java.util.concurrent.TimeUnit.SECONDS.sleep(1);
        JFrame infoPanel = new JFrame();
        JTextField pigsCount = new JTextField("Number of animals: " + this.numberOfAnimals);
        JTextField dayCount = new JTextField("Day: " + this.day);
        infoPanel.add(dayCount);
        infoPanel.add(pigsCount);

        infoPanel.setVisible(true);


        for (int i = 1; i <= numberOfDays; i++){
            this.nextDay();
            day++;
            System.out.println("Day: " + day);
            System.out.println(map.drawMap());
            panel.repaint();
            System.out.println(this.numberOfAnimals);
            pigsCount.setText("Number of animals: " + this.numberOfAnimals);
            dayCount.setText("Day: " + this.day);
            java.util.concurrent.TimeUnit.MILLISECONDS.sleep(50);
        }
    }
}
