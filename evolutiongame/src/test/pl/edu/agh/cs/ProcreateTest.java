package pl.edu.agh.cs;

import org.junit.Test;

import java.util.Arrays;

public class ProcreateTest {

    @Test
    public void procreateTest(){
        WorldMap map = new WorldMap(new Vector2D(10,10));
        Animal fox = new Animal(map, new Vector2D(4,4));
        Animal foxie = new Animal(map, new Vector2D(4,4));
        System.out.println(Arrays.toString(fox.genotype));
        System.out.println(Arrays.toString(foxie.genotype));
        Animal foxJunior = fox.procreate(foxie);
        System.out.println(foxJunior.getPosition());
        System.out.println(foxJunior.getEnergy());
        System.out.println(foxJunior.getOrientation());
        System.out.println(Arrays.toString(foxJunior.genotype));
    }
}
