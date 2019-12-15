package pl.edu.agh.cs.visualization;

import pl.edu.agh.cs.Animal;
import pl.edu.agh.cs.Grass;
import pl.edu.agh.cs.WorldMap;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {
    public WorldMap map;
    public JFrame frame;

    public RenderPanel(WorldMap map, JFrame frame){
        this.map = map;
        this.frame = frame;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setSize((frame.getWidth()), frame.getHeight() -38);
        int width = this.getWidth();
        int height = this.getHeight();
        int widthScale = Math.round(width / map.width);
        int heightScale = height / map.height;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, width, height);

        g.setColor(new Color(0, 160, 7));
        for (Grass grass : map.grassSet){
            int y = grass.getPosition().y * heightScale;
            int x = grass.getPosition().x * widthScale;
            g.fillRect(x, y, widthScale, heightScale);

        }

        for(Animal animal : map.animalsList){
            g.setColor(Color.black);
            int y = animal.getPosition().y * heightScale;;
            int x = animal.getPosition().x * widthScale;;
            g.fillOval(x, y, widthScale, heightScale);
        }
    }
}
