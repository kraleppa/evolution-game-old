package pl.edu.agh.cs;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.edu.agh.cs.visualization.RenderPanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Simulation {
    private WorldMap map;
    private int numberOfDays;
    private int refreshTime;

    private int day;
    private int numberOfAnimals;
    private int totalNumberOfAnimals;
    private int maxNumberOfAnimals;

    public Simulation(int numberOfDays, int numberOfStartAnimals, int refreshTime){
        this.numberOfDays = numberOfDays;
        this.numberOfAnimals = numberOfStartAnimals;
        this.refreshTime = refreshTime;
        this.day = 0;
        this.totalNumberOfAnimals = numberOfStartAnimals;
        this.maxNumberOfAnimals = numberOfStartAnimals;
        JSON mapDetails = new JSON();
        Random random = new Random();
        this.map = new WorldMap(new Vector2D(mapDetails.width - 1, mapDetails.height - 1), mapDetails.startEnergy, mapDetails.moveEnergy, mapDetails.plantEnergy, mapDetails.jungleRatio);
        for (int i = 0; i < numberOfStartAnimals; i++){
            map.place(new Animal(map, new Vector2D(random.nextInt(map.width), random.nextInt(map.height))));
        }
    }

    public void nextDay(){
        int deadAnimals = map.clearDeadAnimals();
        map.turnAllAnimals();
        map.moveAllAnimals();
        map.eatAll();
        int bornAnimals = map.procreateAll();
        map.generateGrass();
        day++;
        numberOfAnimals += bornAnimals - deadAnimals;
        totalNumberOfAnimals += bornAnimals;
        if (numberOfAnimals > maxNumberOfAnimals){
            maxNumberOfAnimals = numberOfAnimals;
        }
    }

    public void startSimulation() throws InterruptedException {
        System.out.println("Day: " + day);
        System.out.println(this.map.drawMap());
        JFrame frame = new JFrame();
        frame.setSize(1920,1080);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RenderPanel panel = new RenderPanel(map, frame);
        frame.add(panel);
        frame.setVisible(true);
        frame.setLayout(new GridLayout());

        JPanel infoPanel = new JPanel();
        JLabel dayCount = new JLabel("Day: " + this.day);
        JLabel pigsCount = new JLabel("Number of animals: " + this.numberOfAnimals);
        JLabel totalPigsCount = new JLabel("Total number of animals: " + this.totalNumberOfAnimals);
        JLabel maxNumberOfPigs = new JLabel("Max number of animals: " + this.maxNumberOfAnimals);

        infoPanel.setSize((int) (0.5 * frame.getWidth()),500);
        infoPanel.add(dayCount);
        infoPanel.add(pigsCount);
        infoPanel.add(totalPigsCount);
        infoPanel.add(maxNumberOfPigs);
        infoPanel.setLayout(new GridLayout(20, 1));
        infoPanel.setVisible(true);

        frame.add(infoPanel);

        java.util.concurrent.TimeUnit.SECONDS.sleep(1);


        for (int i = 1; i <= numberOfDays; i++){
            this.nextDay();
            System.out.println("Day: " + day);
            System.out.println(map.drawMap());
            panel.repaint();
            System.out.println(this.numberOfAnimals);
            pigsCount.setText("Number of animals: " + this.numberOfAnimals);
            dayCount.setText("Day: " + this.day);
            totalPigsCount.setText("Total number of animals: " + this.totalNumberOfAnimals);
            maxNumberOfPigs.setText("Max number of animals: " + this.maxNumberOfAnimals);
            java.util.concurrent.TimeUnit.MILLISECONDS.sleep(this.refreshTime);
        }
    }
}
