package pl.edu.agh.cs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;


public class MovementTest {
    @Test
    public void movementTestBasics(){
        Integer[] directions = new Integer[]{2,0,0,0,0,0};
        WorldMap worldMap = new WorldMap(new Vector2D(6,6));
        worldMap.place(new Animal(worldMap, new Vector2D(1,2)));
        worldMap.place(new Animal(worldMap, new Vector2D(3,0)));
        worldMap.place(new Animal(worldMap, new Vector2D(4,4)));
        worldMap.run(directions);
        Assert.assertTrue(worldMap.isOccupied(new Vector2D(3,2)));
        Assert.assertTrue(worldMap.isOccupied(new Vector2D(4,6)));
        for (int i = 0; i <= 6; i++){
            for(int j = 0; j <= 6; j++){
                if (!((i == 3 && j == 2) || (i == 4 && j == 6))){
                    Assert.assertFalse(worldMap.isOccupied(new Vector2D(i,j)));
                }
            }
        }
    }

    @Test
    public void EdgeTest(){
        WorldMap map = new WorldMap(new Vector2D(6,6));
        Animal rocky = new Animal(map, new Vector2D(5,5));
        map.place(rocky);
        rocky.move();
        Assert.assertEquals(rocky, map.objectAt(new Vector2D(5,6)));
        rocky.move();
        Assert.assertEquals(rocky, map.objectAt(new Vector2D(5,0)));

        Animal fox = new Animal(map, new Vector2D(5, 0));
        map.place(fox);
        fox.turnAround(2);
        fox.move();
        Assert.assertEquals(fox, map.objectAt(new Vector2D(6, 0)));
        fox.move();
        Assert.assertEquals(fox, map.objectAt(new Vector2D(0,0)));

        Animal bison = new Animal(map, new Vector2D(0,0));
        map.place(bison);
        bison.turnAround(5);
        bison.move();

        Assert.assertEquals(bison, map.objectAt(new Vector2D(6,6)));
        //0,6

        WorldMap map2 = new WorldMap(new Vector2D(6,6));

        Animal horse = new Animal(map2, new Vector2D(6,6));
        map2.place(horse);
        horse.turnAround(1);
        horse.move();
        Assert.assertEquals(horse, map2.objectAt(new Vector2D(0,0)));

        Animal pig = new Animal(map2, new Vector2D(6,0));
        map2.place(pig);
        pig.turnAround(3);
        pig.move();
        Assert.assertEquals(pig, map2.objectAt(new Vector2D(0,6)));

        WorldMap map3 = new WorldMap(new Vector2D(6,6));
        Animal sheep =  new Animal(map3, new Vector2D(0,6));
        map3.place(sheep);
        sheep.turnAround(7);
        sheep.move();
        Assert.assertEquals(sheep, map3.objectAt(new Vector2D(6,0)));


    }

}
