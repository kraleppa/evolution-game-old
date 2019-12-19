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

    JFrame frame;

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


        Button button = new Button("Start Simulation");
        button.setBackground(Color.LIGHT_GRAY);
        frame.add(button);
        frame.setLayout(new GridLayout(7, 2, 10, 10));
        frame.setSize(500,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
    }

}
