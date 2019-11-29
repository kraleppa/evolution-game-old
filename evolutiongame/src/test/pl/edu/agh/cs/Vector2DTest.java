package pl.edu.agh.cs;
import org.junit.Assert;
import org.junit.Test;

public class Vector2DTest {
    Vector2D rootVector = new Vector2D(1,1);
    @Test
    public void equalsTest(){
        Assert.assertTrue(new Vector2D(5,5 ).equals(new Vector2D(5,5)));
        Assert.assertFalse(new Vector2D(3,4).equals(new Vector2D(4,2)));
        Assert.assertFalse(new Vector2D(3,4).equals(null));
        Assert.assertFalse(new Vector2D(3, 4).equals(new String("alamakota")));
    }

    @Test
    public void precedesTest(){

        Assert.assertFalse(rootVector.precedes(new Vector2D(0,1)));
        Assert.assertFalse(rootVector.precedes(new Vector2D(0,0)));
        Assert.assertFalse(rootVector.precedes(new Vector2D(1,0)));
        Assert.assertTrue(rootVector.precedes(new Vector2D(1,3)));
        Assert.assertTrue(rootVector.precedes(new Vector2D(2,1)));
        Assert.assertTrue(rootVector.precedes(new Vector2D(3,3)));
    }

    @Test
    public void followsTest(){
        Assert.assertTrue(rootVector.follows(new Vector2D(0,1)));
        Assert.assertTrue(rootVector.follows(new Vector2D(0,0)));
        Assert.assertTrue(rootVector.follows(new Vector2D(1,0)));
        Assert.assertFalse(rootVector.follows(new Vector2D(1,2)));
        Assert.assertFalse(rootVector.follows(new Vector2D(2,2)));
        Assert.assertFalse(rootVector.follows(new Vector2D(2,1)));
    }

    @Test
    public void upperRightTest(){
        Assert.assertEquals(new Vector2D(1,2), rootVector.upperRight(new Vector2D(0,2)));
        Assert.assertEquals(new Vector2D(2,1), rootVector.upperRight(new Vector2D(2,0)));
        Assert.assertEquals(new Vector2D(2,2), rootVector.upperRight(new Vector2D(2,2)));
    }

    @Test
    public void loweLeftTest(){
        Assert.assertEquals(new Vector2D(0,1), rootVector.lowerLeft(new Vector2D(0,2)));
        Assert.assertEquals(new Vector2D(1,0), rootVector.lowerLeft(new Vector2D(2,0)));
        Assert.assertEquals(new Vector2D(0,0), rootVector.lowerLeft(new Vector2D(0,0)));
    }

    @Test
    public void addTest(){
        Assert.assertEquals(new Vector2D(1,1), rootVector.add(new Vector2D(0,0)));
        Assert.assertEquals(new Vector2D(1,2), rootVector.add(new Vector2D(0,1)));
        Assert.assertEquals(new Vector2D(2,1), rootVector.add(new Vector2D(1,0)));
        Assert.assertEquals(new Vector2D(0,1), rootVector.add(new Vector2D(-1,0)));
        Assert.assertEquals(new Vector2D(1,0), rootVector.add(new Vector2D(0,-1)));
        Assert.assertEquals(new Vector2D(0,0), rootVector.add(new Vector2D(-1,-1)));
    }

    @Test
    public void subtractTest(){
        Assert.assertEquals(new Vector2D(1,1), rootVector.subtract(new Vector2D(0,0)));
        Assert.assertEquals(new Vector2D(1,0), rootVector.subtract(new Vector2D(0,1)));
        Assert.assertEquals(new Vector2D(0,1), rootVector.subtract(new Vector2D(1,0)));
        Assert.assertEquals(new Vector2D(0,0), rootVector.subtract(new Vector2D(1,1)));
        Assert.assertEquals(new Vector2D(1,2), rootVector.subtract(new Vector2D(0,-1)));
        Assert.assertEquals(new Vector2D(2,1), rootVector.subtract(new Vector2D(-1,0)));
        Assert.assertEquals(new Vector2D(2,2), rootVector.subtract(new Vector2D(-1,-1)));
    }

    @Test
    public void oppositeTest(){
        Assert.assertEquals(new Vector2D(0,-1), new Vector2D(0,1).opposite());
        Assert.assertEquals(new Vector2D(-1,0), new Vector2D(1,0).opposite());
        Assert.assertEquals(new Vector2D(-1,-1), new Vector2D(1,1).opposite());
        Assert.assertEquals(new Vector2D(1,0), new Vector2D(-1,0).opposite());
        Assert.assertEquals(new Vector2D(0,1), new Vector2D(0,-1).opposite());
        Assert.assertEquals(new Vector2D(1,1), new Vector2D(-1,-1).opposite());
    }
}
