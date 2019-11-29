package pl.edu.agh.cs;

import org.junit.Assert;
import org.junit.Test;

public class JSONTest {
    @Test
    public void importTest(){
        JSON json = new JSON();
        Assert.assertTrue(json.height != null);
        Assert.assertTrue(json.width != null);
        Assert.assertTrue(json.jungleRatio != null);
        Assert.assertTrue(json.startEnergy != null);
        Assert.assertTrue(json.moveEnergy != null);
        Assert.assertTrue(json.plantEnergy != null);
        System.out.println("height: " + json.height);
        System.out.println("width: "+ json.width);
        System.out.println("jungle ratio: " + json.jungleRatio);
        System.out.println("start energy: " + json.startEnergy);
        System.out.println("move energy: " + json.moveEnergy);
        System.out.println("plant energy: " + json.plantEnergy);
    }
}
