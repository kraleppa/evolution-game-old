package pl.edu.agh.cs;

import org.junit.Test;

public class AnimalTest {
    @Test
    public void turnAroundAutoTest(){
        WorldMap map = new WorldMap(new Vector2D(8,8));
        Integer[] genotype = new Integer[]{0,1,3,5,1};
        Animal fox = new Animal(map, new Vector2D(6,6), genotype);
        System.out.println(fox);
        fox.turnAroundAuto();
        System.out.println(fox);
    }
}
