package Model;

import Control.PlayerManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;


public abstract class OldGhost {
    Point pos;
    Point basePos;
    Point target;
    PlayerManager pl;
    Point prevCell;
    int speed;
    String getDir;
    BufferedImage blueG, greenG, purpleG, redG;


    public boolean checkCollision(Point pointGet) {
        return pos.equals(pointGet);
    }

    public boolean isTrail(Point p) {

        return Map.mapTileNum[p.x/24][p.y/24] == 1;
    }

    public abstract void chaseMode();

    public abstract void scatterMode();

//    public void run() {
//        int randomIndex = (int) (Math.random() * Map.pathPoint().size());
//
//        target = Map.pathPoint().get(randomIndex);
//    }
//
//    public Point getNextMove() {
//        String currentDirection = "up";
//
//        HashMap<String, Point> availablePoints = getAvailableDirection(currentDirection);
//
//        Point bestPoint = null;
//        Integer minDistance = Integer.MAX_VALUE;
//
//        for (Point nextPoint : availablePoints.values()) {
//            Integer distance = (int) nextPoint.distance(target);
//
//            if (distance < minDistance) {
//                minDistance = distance;
//                bestPoint = nextPoint;
//            }
//        }
//
//        return bestPoint;
//    }
//
//
//    public HashMap<String, Point> getAvailableDirection(String direction) {
//        HashMap<String, Point> availablePoint = new HashMap<>();
//        Point next = new Point(pos.x + 24, pos.y);
//        if (isTrail(next) && direction != "left")
//            availablePoint.put("right", new Point(next));
//        next.setLocation(pos.x - 24, pos.y);
//        if (isTrail(next) && direction != "right")
//            availablePoint.put("left", new Point(next));
//        next.setLocation(pos.x, pos.y + 24);
//        if (isTrail(next) && direction != "up")
//            availablePoint.put("down", new Point(next));
//        next.setLocation(pos.x, pos.y - 24);
//        if (isTrail(next) && direction != "down")
//            availablePoint.put("up", new Point(next));
//
//        if (availablePoint.isEmpty()) {
//            if (direction.equals("right")) {
//                availablePoint.put("left", new Point(pos.x -24, pos.y));
//            }
//            if (direction.equals("left")) {
//                next.setLocation(pos.x + 24, pos.y);
//                availablePoint.put("right", new Point(next));
//            }
//            if (direction.equals("up")) {
//                next.setLocation(pos.x, pos.y + 24);
//                availablePoint.put("down", new Point(next));
//            }
//            if (direction.equals("down")) {
//                next.setLocation(pos.x, pos.y - 24);
//                availablePoint.put("up", new Point(next));
//            }
//        }
//        return availablePoint;
//    }


}

