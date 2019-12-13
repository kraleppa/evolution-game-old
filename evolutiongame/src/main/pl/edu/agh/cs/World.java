package pl.edu.agh.cs;

import java.util.Arrays;

//0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7
//0, 0, 0, 0, 3, 3, 3, 0, 1, 1, 4, 4, 4, 4, 4, 2, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 0, 0, 1, 1
public class World {
    public static void main(String[] args) {

        WorldMap map = new WorldMap(new Vector2D(5,5));
        Animal fox = new Animal(map, new Vector2D(2,2), new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 7, 7, 7, 7});
        Animal sheep = new Animal(map, new Vector2D(2,2), new Integer[]{0, 0, 0, 0, 3, 3, 3, 0, 1, 1, 4, 4, 4, 4, 4, 2, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 6, 6, 0, 0, 1, 1});
        map.place(fox);
        map.place(sheep);
        map.place(new Animal(map));
        map.place(new Animal(map));
        map.place(new Animal(map));
        map.place(new Animal(map));

        map.generateGrass();
        Simulation sim = new Simulation(map);
        sim.startSimulation(100);






    }

}
