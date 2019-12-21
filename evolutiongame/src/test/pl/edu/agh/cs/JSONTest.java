package pl.edu.agh.cs;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.cs.visualization.WriteJSON;

public class JSONTest {
    @Test
    public void importTest(){
        WriteJSON.createJSON();
        JSON json = new JSON();
    }
}
