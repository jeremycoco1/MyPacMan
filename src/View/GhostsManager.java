package View;

import Control.CollisionChecker;
import Model.Entity;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static View.SettingsDimension.*;

public class GhostsManager extends Entity {
    private CollisionChecker cc;
    private Random random;


    // Positions initiales des fantômes
    private int[][] ghostPositions = {
            {10 * tilesSize, 10 * tilesSize}, // Red ghost
            {12 * tilesSize, 10 * tilesSize}, // Blue ghost
            {14 * tilesSize, 10 * tilesSize}, // Purple ghost
            {12 * tilesSize, 9 * tilesSize}  // Green ghost
    };
    public int ghostSpeed = 2;
    private String[] ghostDirections = {"up", "down", "left", "right"};
    private String[] currentDirections = new String[4];
    private boolean[] isVulnerable = new boolean[4];

    PlayerManager pl;

    public GhostsManager(PlayerManager playerManager) {

        ghostSize = 24;
        this.pl = playerManager;
        cc = CollisionChecker.getInstance();
        random = new Random();
        solidArea = new Rectangle(0, 0, 20, 20);
        speed = ghostSpeed;

        initializeGhostsPos();
        loadGhostImages();
    }

    public void initializeGhostsPos() {
        // Initialisation des positions
        redGhostPosX = ghostPositions[0][0];
        redGhostPosY = ghostPositions[0][1];
        blueGhostPosX = ghostPositions[1][0];
        blueGhostPosY = ghostPositions[1][1];
        purpleGhostPosX = ghostPositions[2][0];
        purpleGhostPosY = ghostPositions[2][1];
        greenGhostPosX = ghostPositions[3][0];
        greenGhostPosY = ghostPositions[3][1];

        // Initialisation des directions aléatoires
        for (int i = 0; i < currentDirections.length; i++) {
            currentDirections[i] = ghostDirections[random.nextInt(ghostDirections.length)];
        }
    }

    private void loadGhostImages() {
        try {
            redG = ImageIO.read(getClass().getResourceAsStream("/ghosts/redG.png"));
            blueG = ImageIO.read(getClass().getResourceAsStream("/ghosts/blueG.png"));
            purpleG = ImageIO.read(getClass().getResourceAsStream("/ghosts/purpleG.png"));
            greenG = ImageIO.read(getClass().getResourceAsStream("/ghosts/greenG.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateGhostPosition(int ghostIndex) {
        int[] positions = {redGhostPosX, redGhostPosY, blueGhostPosX, blueGhostPosY,
                purpleGhostPosX, purpleGhostPosY, greenGhostPosX, greenGhostPosY};

        // Change direction aléatoirement (10% de chance à chaque mise à jour)
        if (random.nextInt(100) < 10) {
            currentDirections[ghostIndex] = ghostDirections[random.nextInt(ghostDirections.length)];
        }

        // Sauvegarde de l'ancienne position
        int oldX = positions[ghostIndex * 2];
        int oldY = positions[ghostIndex * 2 + 1];

        // Mise à jour de la position en fonction de la direction
        switch (currentDirections[ghostIndex]) {
            case "up":
                positions[ghostIndex * 2 + 1] -= speed;
                break;
            case "down":
                positions[ghostIndex * 2 + 1] += speed;
                break;
            case "left":
                positions[ghostIndex * 2] -= speed;
                if (positions[ghostIndex * 2] <= 8) {
                    positions[ghostIndex * 2] = 24 * 25 - 8;
                }
                break;
            case "right":
                positions[ghostIndex * 2] += speed;
                if (positions[ghostIndex * 2] >= 24 * 25 - 22) {
                    positions[ghostIndex * 2] = 8;
                }
                break;
        }

        // Vérification des collisions
        pcPosX = positions[ghostIndex * 2];
        pcPosY = positions[ghostIndex * 2 + 1];
        direction = currentDirections[ghostIndex];  // Ajoutez cette ligne
        collisionOn = false;
        cc.checkTile(this);

        // Si collision détectée, revenir à l'ancienne position et changer de direction
        if (collisionOn) {
            positions[ghostIndex * 2] = oldX;
            positions[ghostIndex * 2 + 1] = oldY;
            currentDirections[ghostIndex] = ghostDirections[random.nextInt(ghostDirections.length)];
        }

        // Mise à jour des positions des fantômes
        redGhostPosX = positions[0];
        redGhostPosY = positions[1];
        blueGhostPosX = positions[2];
        blueGhostPosY = positions[3];
        purpleGhostPosX = positions[4];
        purpleGhostPosY = positions[5];
        greenGhostPosX = positions[6];
        greenGhostPosY = positions[7];
    }

    public void updateGhosts() {
        for (int i = 0; i < 4; i++) {
            updateGhostPosition(i);
        }
    }

    public int getGhostCount() {
        return 4; // Il y a 4 fantômes dans notre jeu
    }


    public void setVulnerable(boolean vulnerable) {
        for (int i = 0; i < 4; i++) {
            isVulnerable[i] = vulnerable;
        }
    }

    public void resetPosition(int ghostIndex) {
        // Réinitialiser la position du fantôme à sa position initiale
        switch (ghostIndex) {
            case 0:
                redGhostPosX = ghostPositions[0][0];
                redGhostPosY = ghostPositions[0][1];
                break;
            case 1:
                blueGhostPosX = ghostPositions[1][0];
                blueGhostPosY = ghostPositions[1][1];
                break;
            case 2:
                purpleGhostPosX = ghostPositions[2][0];
                purpleGhostPosY = ghostPositions[2][1];
                break;
            case 3:
                greenGhostPosX = ghostPositions[3][0];
                greenGhostPosY = ghostPositions[3][1];
                break;
        }
    }
    public void drawGhosts(Graphics2D g2) {
        // Modifier la méthode pour dessiner les fantômes en bleu quand ils sont vulnérables
        for (int i = 0; i < 4; i++) {
            int x , y ;
            BufferedImage ghostImage;

            switch (i) {
                case 0:
                    x = redGhostPosX;
                    y = redGhostPosY;
                    ghostImage = isVulnerable[i] ? blueG : redG;
                    break;
                case 1:
                    x = blueGhostPosX;
                    y = blueGhostPosY;
                    ghostImage = isVulnerable[i] ? blueG : blueG;
                    break;
                case 2:
                    x = purpleGhostPosX;
                    y = purpleGhostPosY;
                    ghostImage = isVulnerable[i] ? blueG : purpleG;
                    break;
                case 3:
                    x = greenGhostPosX;
                    y = greenGhostPosY;
                    ghostImage = isVulnerable[i] ? blueG : greenG;
                    break;
                default:
                    continue;
            }

            g2.drawImage(ghostImage, x, y, ghostSize, ghostSize, null);
        }
    }

}
