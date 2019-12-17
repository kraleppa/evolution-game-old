package pl.edu.agh.cs.visualization;

import pl.edu.agh.cs.Animal;
import pl.edu.agh.cs.Grass;
import pl.edu.agh.cs.Vector2D;
import pl.edu.agh.cs.WorldMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RenderPanel extends JPanel {
    public WorldMap map;
    public JFrame frame;
    private Image animal;
    private Image bush;
    private Image jungle;
    private Image steppe;
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

        try {
            this.steppe = ImageIO.read(new File("steppe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.steppe = this.steppe.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);



    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, this.width, this.height);

        for (int x = 0; x < this.map.width; x++){
            for (int y = 0; y < this.map.height; y++){
                if (map.isPositionJungle(new Vector2D(x,y))){
                    g.drawImage(this.jungle, x * widthScale, y * heightScale, this);
                }
                else{
                    g.drawImage(this.steppe, x * widthScale, y * heightScale, this);
                }
                if (map.objectAt(new Vector2D(x, y)) != null){
                    if (map.objectAt(new Vector2D(x, y)) instanceof Animal){
                        g.drawImage(this.animal, x * widthScale, y * heightScale, this);
                    }
                    if (map.objectAt(new Vector2D(x,y)) instanceof ArrayList){
                        g.drawImage(this.animal, x * widthScale, y * heightScale, this);
                    }
                    if (map.objectAt(new Vector2D(x, y)) instanceof Grass){
                        g.drawImage(this.bush, x * widthScale, y * heightScale, this);
                    }

                }
            }
        }
    }

}
