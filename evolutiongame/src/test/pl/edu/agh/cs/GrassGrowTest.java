package pl.edu.agh.cs;

import org.junit.Assert;
import org.junit.Test;

public class GrassGrowTest {
    @Test
    public void growUnderAnimalTest(){
        WorldMap map = new WorldMap(new Vector2D(8,8));
        map.place(new Animal(map, new Vector2D(4,4)));
        for (int i = 0; i < 10000; i++){
            for (Grass grass : map.grassSet){
                Assert.assertNotEquals(new Vector2D(4,4), grass.getPosition());
            }
            map.generateGrass();
        }
    }
}
