package pl.edu.agh.cs;

import org.junit.Assert;
import org.junit.Test;


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

}
