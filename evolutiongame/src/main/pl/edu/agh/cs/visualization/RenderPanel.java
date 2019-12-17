package pl.edu.agh.cs.visualization;

import pl.edu.agh.cs.Animal;
import pl.edu.agh.cs.Grass;
import pl.edu.agh.cs.WorldMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RenderPanel extends JPanel {
    public WorldMap map;
    public JFrame frame;
    private Image animal;
    private Image bush;
    private Image jungle;
    private int width;
    private int height;
    private int widthScale;
    private int heightScale;

    public RenderPanel(WorldMap map, JFrame frame){
        this.map = map;
        this.frame = frame;
        this.setSize((frame.getWidth()), frame.getHeight() -38);
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.widthScale = Math.round(width / map.width);
        this.heightScale = height / map.height;

        try {
            this.animal = ImageIO.read(new File("Animal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.animal = this.animal.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);

        try {
            this.bush = ImageIO.read(new File("Bush.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bush = this.bush.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);

        try {
            this.jungle = ImageIO.read(new File("Jungle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.jungle = this.jungle.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);



    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.width, this.height);

        for (Grass grass : map.grassSet){
            int y = grass.getPosition().y * heightScale;
            int x = grass.getPosition().x * widthScale;
            g.drawImage(this.bush, x, y, this);
        }

        for(Animal animal : map.animalsList){
            g.setColor(Color.black);        this.setSize((frame.getWidth()), frame.getHeight() -38);
        int width = this.getWidth();
        int height = this.getHeight();
        int widthScale = Math.round(width / map.width);
        int heightScale = height / map.height;
            int y = animal.getPosition().y * heightScale;;
            int x = animal.getPosition().x * widthScale;;
            g.drawImage(this.animal, x, y, this);
        }
    }
}
