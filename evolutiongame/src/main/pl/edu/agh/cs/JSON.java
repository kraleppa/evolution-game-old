package pl.edu.agh.cs;

        /*Rozmiar mapy (width, height)
        ilość energii początkowej zwierząt (startEnergy)
        ilość energii traconej w każdym dniu (moveEnergy)
        ilość energii zyskiwanej przy zjedzeniu rośliny (plantEnergy)
        proporcje dżungli do sawanny (jugnleRatio)
        */

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JSON {
    public final Integer width;
    public final Integer height;
    public final Integer startEnergy;
    public final Integer moveEnergy;
    public final Integer plantEnergy;
    public final Double jungleRatio;
    private final String fileName;

    public JSON(String fileName) {
        Integer width = null;
        Integer height = null;
        Integer startEnergy = null;
        Integer moveEnergy = null;
        Integer plantEnergy = null;
        Double jungleRatio = null;
        this.fileName = fileName;

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(fileName)) {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            height = Integer.parseInt((String) jsonObject.get("height"));
            width = Integer.parseInt((String) jsonObject.get("width"));
            startEnergy = Integer.parseInt((String) jsonObject.get("startEnergy"));
            moveEnergy = Integer.parseInt((String) jsonObject.get("moveEnergy"));
            plantEnergy = Integer.parseInt((String) jsonObject.get("plantEnergy"));
            jungleRatio = Double.parseDouble((String) jsonObject.get("jungleRatio"));

        } catch (IOException | ParseException e) {
            e.printStackTrace();

        }finally {
            this.width = width;
            this.height = height;
            this.startEnergy = startEnergy;
            this.jungleRatio = jungleRatio;
            this.moveEnergy = moveEnergy;
            this.plantEnergy =  plantEnergy;
        }
    }

    public JSON(){
        this("simulationParameters.json");
    }
}

