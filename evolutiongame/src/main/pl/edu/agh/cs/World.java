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

        WorldMap map = new WorldMap(new Vector2D(8,8));
        Animal fox = new Animal(map, new Vector2D(1,2), new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7});
        Animal sheep = new Animal(map, new Vector2D(2,2), new Integer[]{0, 0, 0, 0, 3, 3, 3, 0, 1, 1, 4, 4, 4, 4, 4, 2, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 0, 0, 1, 1});
        map.place(fox);
        map.place(sheep);
        map.place(new Animal(map, new Vector2D(2,1)));
        map.place(new Animal(map, new Vector2D(4,2)));
        map.place(new Animal(map, new Vector2D(2, 0)));
        map.place(new Animal(map,  new Vector2D(0,5)));
        Simulation sim = new Simulation(map);
        sim.startSimulation(100);
        /*System.out.println(map.drawMap());
        JFrame frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RenderPanel panel = new RenderPanel(map, frame);
        frame.add(panel);
        frame.setVisible(true);
        java.util.concurrent.TimeUnit.SECONDS.sleep(1);
        map.turnAllAnimals();
        map.moveAllAnimals();
        panel.repaint();
        java.util.concurrent.TimeUnit.SECONDS.sleep(1);
        map.turnAllAnimals();
        map.moveAllAnimals();
        panel.repaint();

        System.out.println(map.drawMap());
        ((1 + 1) + 1) - 1
        System.out.println(map.drawMap());*/





    }

}
