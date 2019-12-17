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
    private Image cactus;
    private Image animal2;
    private Image animal3;
    private Image animal4;
    private Image animal5;
    private Image animal5Plus;
    private int width;
    private int height;
    private int widthScale;
    private int heightScale;

    public RenderPanel(WorldMap map, JFrame frame){
        this.map = map;
        this.frame = frame;
        this.setSize((int) ((frame.getWidth()) * 0.5), frame.getHeight() -38);
        this.width = this.getWidth();
        this.height = this.getHeight();
        this.widthScale = Math.round(width / map.width);
        this.heightScale = height / map.height;

        try {
            this.animal = ImageIO.read(new File("Pig.png"));
            this.animal2 = ImageIO.read(new File("Pig2.png"));
            this.animal3 = ImageIO.read(new File("Pig3.png"));
            this.animal4 = ImageIO.read(new File("Pig4.png"));
            this.animal5 = ImageIO.read(new File("Pig5.png"));
            this.animal5Plus = ImageIO.read(new File("Pig5plus.png"));
            this.bush = ImageIO.read(new File("Bush.png"));
            this.cactus = ImageIO.read(new File("SteppeBush.png"));
            this.jungle = ImageIO.read(new File("Jungle.png"));
            this.steppe = ImageIO.read(new File("steppe.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.animal = this.animal.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);
        this.animal2 = this.animal2.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);
        this.animal3 = this.animal3.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);
        this.animal4 = this.animal4.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);
        this.animal5 = this.animal5.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);
        this.animal5Plus = this.animal5Plus.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);
        this.bush = this.bush.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);
        this.jungle = this.jungle.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);
        this.steppe = this.steppe.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);
        this.cactus = this.cactus.getScaledInstance(widthScale, heightScale, Image.SCALE_DEFAULT);
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
                        int size = ((ArrayList) map.objectAt(new Vector2D(x,y))).size();
                        switch (size){
                            case 2:  g.drawImage(this.animal2, x * widthScale, y * heightScale, this); break;
                            case 3:  g.drawImage(this.animal3, x * widthScale, y * heightScale, this); break;
                            case 4:  g.drawImage(this.animal4, x * widthScale, y * heightScale, this); break;
                            case 5:  g.drawImage(this.animal5, x * widthScale, y * heightScale, this); break;
                            default:  g.drawImage(this.animal5Plus, x * widthScale, y * heightScale, this); break;
                        }

                    }
                    if (map.objectAt(new Vector2D(x, y)) instanceof Grass && map.isPositionJungle(new Vector2D(x,y))){
                        g.drawImage(this.bush, x * widthScale, y * heightScale, this);
                    }
                    if (map.objectAt(new Vector2D(x, y)) instanceof Grass && !map.isPositionJungle(new Vector2D(x,y))){
                        g.drawImage(this.cactus, x * widthScale, y * heightScale, this);
                    }

                }
            }
        }
    }

}
