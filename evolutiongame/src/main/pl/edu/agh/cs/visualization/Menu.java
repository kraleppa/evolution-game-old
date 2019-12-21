package pl.edu.agh.cs.visualization;

import javax.swing.*;
import java.awt.*;

       /* details.put("width", "200");
                details.put("height", "100");
                details.put("jungleRatio", "0.5");
                details.put("startEnergy", "10");
                details.put("moveEnergy", "1");
                details.put("plantEnergy", "2");
*/
public class Menu {

    private JFrame frame;
    private Button saveButton;
    private Button startButton;

    public Menu(){
        frame = new JFrame("Menu");

        Label mapHeight = new Label("Height of Map");
        frame.add(mapHeight);
        TextField heightInsert = new TextField();
        frame.add(heightInsert);

        Label mapWidth = new Label("Width of Map");
        frame.add(mapWidth);

        TextField widthInsert = new TextField();
        frame.add(widthInsert);

        Label jungleRatio = new Label("Jungle Ratio");
        frame.add(jungleRatio);

        TextField jungleRatioInsert = new TextField();
        frame.add(jungleRatioInsert);

        Label startEnergy = new Label("Start Energy of Animal");
        frame.add(startEnergy);

        TextField startEnergyInsert = new TextField();
        frame.add(startEnergyInsert);

        Label moveEnergy = new Label("Move energy of Animal");
        frame.add(moveEnergy);

        TextField moveEnergyInsert = new TextField();
        frame.add(moveEnergyInsert);

        Label plantEnergy = new Label("Plant energy");
        frame.add(plantEnergy);

        TextField plantEnergyInsert = new TextField();
        frame.add(plantEnergyInsert);

        Label days = new Label("Number of days");
        frame.add(days);

        TextField daysInsert = new TextField();
        frame.add(daysInsert);

        Label animals = new Label("Number of start animals");
        frame.add(animals);

        TextField animalsInsert = new TextField();
        frame.add(animalsInsert);

        Label refreshTime = new Label("Refresh time (in miliseconds)");
        frame.add(refreshTime);

        TextField refreshTimeInsert = new TextField();
        frame.add(refreshTimeInsert);



        Button commit = new Button("Save options");
        commit.setBackground(Color.LIGHT_GRAY);
        Button start = new Button("Start simulation");
        start.setBackground(Color.LIGHT_GRAY);

        this.startButton = start;
        this.saveButton = commit;

        frame.add(commit);
        frame.add(start);
        frame.setLayout(new GridLayout(10, 2, 10, 10));
        frame.setSize(500,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
    }

}
