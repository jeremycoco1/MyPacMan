package View;

import Control.KeyHandler;
import Model.Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static View.SettingsDimension.*;

public class PlayerManager extends Entity {

    public int pcPosX;
    public int pcPosY;
    private  static PlayerManager playerInstance;
    KeyHandler keyH=KeyHandler.getInstance();
    private PlayerManager( ) {
        pcPosX = screenWidth / 2 - (tilesSize / 2);
        pcPosY = ((screenHeight / 3)*2) - (tilesSize / 2);
        setDefaultValues();
        getPlayerImage();
    }

    public  static PlayerManager getInstance(){
        if(playerInstance==null){
            playerInstance= new PlayerManager();
        }
        return playerInstance;
    }
    public void setDefaultValues() {
        speed = 3;
        direction = "right";
    }

    public void getPlayerImage() {
        try {
            rightLeft = ImageIO.read(getClass().getResourceAsStream("/pac man/rightLeft.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/pac man/left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/pac man/left_3.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/pac man/right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/pac man/right_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/pac man/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/pac man/down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/pac man/down_3.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/pac man/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/pac man/up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/pac man/up_3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";
            }

            switch (direction) {
                case "up":
                    pcPosY -= speed;
                    break;
                case "down":
                    pcPosY += speed;
                    break;
                case "left":
                    pcPosX -= speed;
                    break;
                case "right":
                    pcPosX += speed;
                    break;
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                }
                if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = rightLeft;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = rightLeft;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
                break;
        }
        g2.drawImage(image, pcPosX, pcPosY, tilesSize-(tilesSize/4), tilesSize-(tilesSize/4), null);
    }
}