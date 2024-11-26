package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {


    public int pcPosX;
    public int pcPosY;

    public Ghost redGhost;
    public Ghost greenGhost;
    public Ghost purpleGhost;
    public Ghost blueGhost;

    public int ghostSize;
    public int speed;

    public BufferedImage blueG, greenG, purpleG, redG;


    public BufferedImage rightLeft, left2, left3, right2, right3, down1, down2, down3, up1, up2, up3;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn;



}
