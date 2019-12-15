package pl.edu.agh.cs;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        for (int i = 0; i < Math.sqrt(map.width + map.height); i++){
            map.generateGrass();
        }
        day++;
    }


    public void startSimulation(int numberOfDays) throws InterruptedException {
        System.out.println("Day: " + day);
        System.out.println(map.drawMap());
        //java.util.concurrent.TimeUnit.SECONDS.sleep(1);

        for (int i = 1; i <= numberOfDays; i++){
            this.nextDay();
            System.out.println("Day: " + day);
            System.out.println(map.drawMap());
            //java.util.concurrent.TimeUnit.SECONDS.sleep(1);
        }
    }



}
