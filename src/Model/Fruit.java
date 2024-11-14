package Model;

import java.awt.image.BufferedImage;

public class Fruit {
    String name;
    public String imagePath;
    public int points;
    public int scoreThreshold;
    public BufferedImage image;

  public  Fruit(String name, String imagePath, int points, int scoreThreshold) {
        this.name = name;
        this.imagePath = imagePath;
        this.points = points;
        this.scoreThreshold = scoreThreshold;
    }
}
