package Control;

import Model.Coins;
import Model.Entity;
import Model.Ghost;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


import static View.SettingsDimension.*;


public class PlayerManager extends Entity {

    KeyHandler keyH;
    CollisionChecker cc;
    Coins coins;
    GhostsManager ghosts;
    FruitManager fruits;
    TileManager tm;
//    Blinky blinky=new Blinky(this);


    private int lives = 3;
    private boolean invincible = false;


    private boolean isGameOver = false;
    private long gameOverDelay = 3000; // Délai de 3 secondes avant reset
    private long deathTime = 0;



    private int lastFruitSpawnScore = 0;  //score d aparition
    private static final int SCORE_INTERVAL_FOR_FRUIT = 200; // Spawn interval
    private static final int MINIMUM_SCORE_FOR_FRUIT = 50;

    public PlayerManager(KeyHandler keyH,CollisionChecker cc,TileManager tm) {


        this.keyH = keyH;
        this.cc = cc;
        this.tm = tm;


        solidArea = new Rectangle();
        solidArea.x = 6;
        solidArea.y = 6;
        solidArea.width = 12;
        solidArea.height = 12;

        this.coins = new Coins(this);
        this.ghosts = new GhostsManager(this, cc);
        this.fruits = new FruitManager(this, tm);

        setDefaultValues();
        getPlayerImage();


    }

    public int getPcPosX() {
        return pcPosX;
    }

    public int getPcPosY() {
        return pcPosY;
    }

    public Coins getCoins() {
        return coins;
    }

//    public Blinky getBlinky(){return blinky;}

    public GhostsManager getGhosts() {
        return ghosts;
    }

    public FruitManager getFruits() {
        return fruits;
    }

    public void setDefaultValues() {
        pcPosX = 13 * tilesSize - tilesSize;
        pcPosY = 21 * tilesSize;
        speed = 2;
        direction = "down";
    }

    private void snapToGrid(String direction) {
        switch (direction) {
            case "up", "down":
                pcPosX = Math.round((float) pcPosX / tilesSize) * tilesSize;
                break;
            case "right", "left":
                pcPosY = Math.round((float) pcPosY / tilesSize) * tilesSize;
                break;
        }
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

    public void handlePowerModeCollision() {

        for (int i = 0; i < getGhosts().getGhostCount(); i++) {

            if (checkGhostCollision(i)) {
                if (coins.powerMode) {
                    ghosts.resetPosition(i);
                    coins.addScore(200);
                } else {
                    die();
                }
            }
        }
    }

    private boolean checkGhostCollision(int ghostIndex) {
        Ghost currentGhost;
        switch (ghostIndex) {
            case 0:
                currentGhost = ghosts.redGhost;
                break;
            case 1:
                currentGhost = ghosts.blueGhost;
                break;
            case 2:
                currentGhost = ghosts.purpleGhost;
                break;
            default:
                currentGhost = ghosts.greenGhost;
                break;
        }

        Rectangle pacmanArea = new Rectangle(pcPosX + solidArea.x,
                pcPosY + solidArea.y,
                solidArea.width,
                solidArea.height);

        Rectangle ghostArea = new Rectangle(currentGhost.x + ghosts.solidArea.x,
                currentGhost.y + ghosts.solidArea.y,
                ghosts.solidArea.width,
                ghosts.solidArea.height);

        return pacmanArea.intersects(ghostArea);
    }

    public void update() {
        fruits.update();
//        blinky.updateGhost();

        if (coins.score >= MINIMUM_SCORE_FOR_FRUIT &&
                coins.score >= lastFruitSpawnScore + SCORE_INTERVAL_FOR_FRUIT) {
            fruits.spawn(coins.score);
            lastFruitSpawnScore = coins.score;
        }


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

            collisionOn = false;
            cc.checkTile(this);
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        pcPosY -= speed;
                        break;
                    case "down":
                        pcPosY += speed;
                        break;
                    case "left":
                        pcPosX -= speed;
                        if (pcPosX <= 8) {
                            pcPosX = 24 * 25 - 8;
                        }
                        break;
                    case "right":
                        pcPosX += speed;
                        if (pcPosX >= 24 * 25 - 22) {
                            pcPosX = 8;
                        }
                        break;
                }

                snapToGrid(direction);

            }
            coins.checkCoins();
            if (coins.powerMode) {
                coins.powerModeTimer--;
                if (coins.powerModeTimer <= 0) {
                    coins.powerMode = false;
                    ghosts.setVulnerable(false);  // Ajouter cette ligne
                }
            }
            handlePowerModeCollision();
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
        g2.drawImage(image, pcPosX, pcPosY, 24, 24, null);
        if (keyH.pausePressed && !isGameOver) {
            drawPauseScreen(g2);
        }
       if (keyH.pausePressed || isGameOver) {
           drawGameOver(g2);
            if (isGameOver) {
                if (System.currentTimeMillis() - deathTime >= gameOverDelay) {
                    if (keyH.upPressed || keyH.downPressed ||
                            keyH.leftPressed || keyH.rightPressed) {
                        resetGame();

                    }
                }
            }

        }

    }



    private void drawPauseScreen(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 180)); // Fond semi-transparent
        g2.fillRect(0, 0, screenWidth, screenHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        String pauseText = "PAUSE";
        FontMetrics fm = g2.getFontMetrics();
        int x = (screenWidth - fm.stringWidth(pauseText)) / 2;
        int y = screenHeight / 2;
        g2.drawString(pauseText, x, y);

        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        String continueText = "Press ENTER to continue";
        fm = g2.getFontMetrics();
        x = (screenWidth - fm.stringWidth(continueText)) / 2;
        y += 40;
        g2.drawString(continueText, x, y);
    }

    public void drawScore(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));

        g2.drawString("SCORE: " + coins.score, 26, tilesSize * 26);

        // Ajouter l'affichage du high score
        String highScoreText = "HIGH SCORE: " + coins.getHighScore();
        FontMetrics fm = g2.getFontMetrics();
        int highScoreX = screenWidth / 2 - fm.stringWidth(highScoreText) / 2;
        g2.drawString(highScoreText, highScoreX, tilesSize * 26);
    }

    public void drawLives(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        int lifeSize = 20;
        int spacing = 25;
        int startX = 10;
        int startY = screenHeight - 30;

        for (int i = 0; i < lives; i++) {
            g2.fillArc(startX + (i * spacing), startY, lifeSize, lifeSize, 30, 300);
        }
    }

    public void die() {

        if (!invincible) {
            lives--;
            deathTime = System.currentTimeMillis();
            if (lives <= 0) {
                handleGameOver();
            } else {
                resetPosition();
                ghosts.initializeGhostsPos();

            }
        }
    }

    private void handleGameOver() {
        isGameOver = true;
        coins.updateHighScore();  // Mettre à jour le high score
//        waitingForInput = true;  // Réinitialiser l'attente d'input
    }


    public void resetGame() {
        lives = 3;
        int previousHighScore = coins.getHighScore();
        coins.score = 0;
        isGameOver = false;
        ghosts.initializeGhostsPos();
        coins = new Coins(this);
        coins.highScore = previousHighScore;
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;

        resetPosition();
    }
    public void drawGameOver(Graphics2D g2) {
        if (isGameOver) {
            g2.setColor(new Color(0, 0, 0, 180)); // Fond semi-transparent
            g2.fillRect(0, 0, screenWidth, screenHeight);

            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 40));
            String gameOverText = "GAME OVER";
            FontMetrics fm = g2.getFontMetrics();
            int x = (screenWidth - fm.stringWidth(gameOverText)) / 2;
            int y = screenHeight / 2;
            g2.drawString(gameOverText, x, y);

            // Score final
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            String scoreText = "Final Score: " + coins.score;
            fm = g2.getFontMetrics();
            x = (screenWidth - fm.stringWidth(scoreText)) / 2;
            y += 40;
            g2.drawString(scoreText, x, y);

            // Message pour recommencer
            if (System.currentTimeMillis() - deathTime >= gameOverDelay) {
//
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Arial", Font.PLAIN, 20));
                String restartText = "Press any key to restart";
                fm = g2.getFontMetrics();
                x = (screenWidth - fm.stringWidth(restartText)) / 2;
                y += 40;
                g2.drawString(restartText, x, y);
            }
        }
    }

    public void resetPosition() {
        pcPosX = 13 * tilesSize - tilesSize;
        pcPosY = 21 * tilesSize;
        direction = "down";
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.leftPressed = false;
        keyH.rightPressed = false;
    }
}
