package pl.edu.agh.cs.JSONServices;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteJSON {
    @SuppressWarnings("unchecked")
    public static void createJSON()
    {
        //parameters
        JSONObject details = new JSONObject();
        details.put("width", "200");
        details.put("height", "100");
        details.put("jungleRatio", "0.5");
        details.put("startEnergy", "10");
        details.put("moveEnergy", "1");
        details.put("plantEnergy", "2");


        try (FileWriter file = new FileWriter("simulationParameters.json")) {
            file.write(details.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
