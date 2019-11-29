package pl.edu.agh.cs;


import org.junit.Assert;
import org.junit.Test;

public class AzimuthTest {

    @Test(expected = IllegalArgumentException.class)
    public void convertTest(){
        String[] args = new String[]{"n", "ne", "e", "se", "s", "sw", "w", "nw"};
        Azimuth[] anserw = new Azimuth[]{Azimuth.N, Azimuth.NE, Azimuth.E, Azimuth.SE, Azimuth.S, Azimuth.SW, Azimuth.W, Azimuth.NW};
        Assert.assertArrayEquals(anserw, Azimuth.convert(args));
        String[] argsWithBug = new String[]{"n", "ne", "e", "se", "bottom", "s", "sw", "w", "nw"};
        Azimuth.convert(argsWithBug);
    }

    @Test
    public void toUnitVectorTest(){
        Assert.assertEquals(new Vector2D(0,1),Azimuth.N.toUnitVector());
        Assert.assertEquals(new Vector2D(1,1),Azimuth.NE.toUnitVector());
        Assert.assertEquals(new Vector2D(1,0),Azimuth.E.toUnitVector());
        Assert.assertEquals(new Vector2D(1,-1),Azimuth.SE.toUnitVector());
        Assert.assertEquals(new Vector2D(0,-1),Azimuth.S.toUnitVector());
        Assert.assertEquals(new Vector2D(-1,-1),Azimuth.SW.toUnitVector());
        Assert.assertEquals(new Vector2D(-1,0),Azimuth.W.toUnitVector());
        Assert.assertEquals(new Vector2D(-1,1),Azimuth.NW.toUnitVector());
    }
}
