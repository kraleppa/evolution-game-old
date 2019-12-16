package pl.edu.agh.cs;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

    @Test
    public void oneAnimalTest(){
        WorldMap map = new WorldMap(new Vector2D(10, 10));
        Animal fox = new Animal(map, new Vector2D(2,2));
        map.place(fox);
        Vector2D oldPosition = new Vector2D(2, 2);
        for (int i = 0; i < 5000; i++){
            fox.turnAroundAuto();
            fox.move();
            boolean flag = false;
            for (Azimuth azimuth : Azimuth.values()){
                if ((map.objectAt(map.betterPosition(oldPosition.add(azimuth.toUnitVector())))) != null){
                    flag = true;
                }
            }
            Assert.assertTrue(flag);
            oldPosition = fox.getPosition();
        }
        System.out.println(fox.getEnergy());
    }

    @Test
    public void grassEaterTest(){
        WorldMap map = new WorldMap(new Vector2D(2, 2));
        map.putGrass(new Vector2D(1,1));
        Animal fox = new Animal(map, new Vector2D(1, 0));
        Animal sheep = new Animal(map, new Vector2D(0,1));
        sheep.turnAround(2);
        Animal goat  = new Animal(map, new Vector2D(1, 2));
        goat.turnAround(4);
        Animal dog = new Animal(map, new Vector2D(2,1));
        dog.turnAround(6);
        Assert.assertTrue(map.objectAt(new Vector2D(1,1)) instanceof Grass);
        map.place(fox);
        map.place(dog);
        map.place(sheep);
        map.place(goat);
        map.moveAllAnimals();

        map.eatAll();

        Assert.assertEquals(33.0, dog.getEnergy(), 0.01);
        Assert.assertEquals(33.0, sheep.getEnergy(), 0.01);
        Assert.assertEquals(33.0, goat.getEnergy(), 0.01);
        Assert.assertEquals(33.0, fox.getEnergy(), 0.01);
        map.moveAllAnimals();
        Assert.assertFalse(map.objectAt(new Vector2D(1,1)) instanceof Grass);

    }

    @Test
    public void grassEatingTest(){
        WorldMap map = new WorldMap(new Vector2D(2,2));
        map.place(new Animal(map, new Vector2D(1, 0)));
        map.putGrass(new Vector2D(1,1));
        map.moveAllAnimals();
        map.eatAll();
        map.moveAllAnimals();
        Assert.assertNull(map.objectAt(new Vector2D(1,1)));
        Assert.assertEquals(0, map.grassSet.size());

    }


}
