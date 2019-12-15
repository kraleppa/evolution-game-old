package pl.edu.agh.cs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ProcreationTest {
    @Test
    //there is a small chance that this test won't work
    public void simpleProcreationTest() {
        for (int i = 0; i < 100; i++) {
            WorldMap map = new WorldMap(new Vector2D(2, 2));
            Animal fox = new Animal(map, new Vector2D(1, 1));
            Animal foxie = new Animal(map, new Vector2D(1, 1));
            Animal foxJunior = fox.procreate(foxie);

            boolean[] eliminatedGenes = new boolean[8];
            Arrays.fill(eliminatedGenes, false);
            for (Integer gen : foxJunior.genotype) {
                eliminatedGenes[gen] = true;
            }

            for (int j = 0; j < 8; j++) {
                System.out.println(Arrays.toString(foxJunior.genotype));
                Assert.assertTrue(eliminatedGenes[j]);
            }
        }
    }
}

