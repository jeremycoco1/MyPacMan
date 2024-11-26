package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static View.SettingsDimension.maxMapCol;
import static View.SettingsDimension.tilesSize;

public class Ghost {
    int MAP_SIZE = 25;
    public int initialX;
    public int initialY;
    int indexX;
    int indexY;
    public int x;
    public int y;
    public int speed;
    public Rectangle solidArea = new Rectangle(0, 0, 20, 20);;
    Random random = new Random();
    private String[] ghostDirections = {"up", "down", "left", "right"};
    public String direction;
    public boolean collisionOn;

    public Ghost(int initialX, int initialY, int speed) {
        this.initialX = initialX;
        this.initialY = initialY;
        this.speed = speed;
        // לאתחל את האינדקס הנכון
        this.indexX=initialX/tilesSize;
        this.indexY=initialY/tilesSize;
       this.direction = ghostDirections[random.nextInt(ghostDirections.length)];

        reset();
    }

    public void reset() {
        this.x = initialX;
        this.y = initialY;
    }



    public boolean isTrail(Point p) {
        return p.x < MAP_SIZE && p.x >= 0 && p.y < MAP_SIZE && p.y >= 0 && Map.mapTileNum[p.x][p.y] == 1;
    }


    public String getNextDirection() {
        HashMap<String, Point> availablePoint = new HashMap<>();
        Point next = new Point( indexX + 1, indexY);
        if (isTrail(next) && direction != "left")
            availablePoint.put("right", new Point(next));
        next.setLocation(indexX - 1, indexY);
        if (isTrail(next) && direction != "right")
            availablePoint.put("left", new Point(next));
        next.setLocation(indexX, indexY + 1);
        if (isTrail(next) && direction != "up")
            availablePoint.put("down", new Point(next));
        next.setLocation(indexX, indexY - 1);
        if (isTrail(next) && direction != "down")
            availablePoint.put("up", new Point(next));

        if (availablePoint.isEmpty()) {
            if (direction.equals("right")) {
                availablePoint.put("left", new Point(indexX, indexY));
            }
            if (direction.equals("left")) {
                next.setLocation(indexX , indexY);
                availablePoint.put("right", new Point(next));
            }
            if (direction.equals("up")) {
                next.setLocation(indexX, indexY + 1);
                availablePoint.put("down", new Point(next));
            }
            if (direction.equals("down")) {
                next.setLocation(indexX, indexY - 1);
                availablePoint.put("up", new Point(next));
            }
        }
        ArrayList<String> keys = new ArrayList<>(availablePoint.keySet());

        String randomKey = keys.get(new Random().nextInt(keys.size()));
        return randomKey;
    }

    int pixelToIndex(int i) {
        return i= i/tilesSize;
    }


    public void updateGhostPosition() {
        int newX = pixelToIndex(x);
        int newY = pixelToIndex(y);
        if (indexX != newX || indexY != newY ) {
            indexX = newX;
            indexY = newY;
            direction = getNextDirection();
        }

        switch (direction) {
            case "up":
                this.y -= speed;
            System.out.println("up");
                break;
            case "down":
                this.y += speed;
                System.out.println("down");
                break;
            case "left":
                this.x -= speed;
                if (this.x <= 8) {
                    this.x = 24 * 25 - 8;
                }
                System.out.println("left");
                break;
            case "right":
                this.x += speed;
                if (this.x >= 24 * 25 - 22) {
                    this.x = 8;
                }
                System.out.println("right");
                break;
        }
    }
}
