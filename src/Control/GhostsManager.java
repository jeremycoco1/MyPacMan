package Control;

import Model.Entity;
import Model.Ghost;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static View.SettingsDimension.*;

public class GhostsManager extends Entity {
    CollisionChecker cc;
    PlayerManager pl;
    Random random;



    private int[][] ghostPositions = {
            {10 * tilesSize, 10 * tilesSize},
            {12 * tilesSize, 10 * tilesSize},
            {14 * tilesSize, 10 * tilesSize},
            {12 * tilesSize, 9 * tilesSize}
    };
    public int ghostSpeed = 2;
    private String[] ghostDirections = {"up", "down", "left", "right"};
    private String[] currentDirections = new String[4];
    private boolean[] isVulnerable = new boolean[4];


    public GhostsManager(PlayerManager playerManager, CollisionChecker cc) {

        ghostSize = 24;
        this.pl = playerManager;
        this.cc = cc;
        random = new Random();
        solidArea = new Rectangle(0, 0, 20, 20);
        speed = ghostSpeed;

        initializeGhostsPos();
        loadGhostImages();
    }


    public void initializeGhostsPos() {
        redGhost = new Ghost(ghostPositions[0][0], ghostPositions[0][1], speed);
        blueGhost = new Ghost(ghostPositions[1][0], ghostPositions[1][1], speed);
        purpleGhost = new Ghost(ghostPositions[2][0], ghostPositions[2][1], speed);
        greenGhost = new Ghost(ghostPositions[3][0], ghostPositions[3][1], speed);
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
        Ghost[] positions = {redGhost, blueGhost, purpleGhost, greenGhost };

        Ghost cur = positions[ghostIndex];





        cur.updateGhostPosition();



    }

    public void updateGhosts() {
        for (int i = 0; i < 4; i++) {
            updateGhostPosition(i);
        }
    }

    public int getGhostCount() {
        return 4;
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
                redGhost.reset();
                break;
            case 1:
                blueGhost.reset();
                break;
            case 2:
                purpleGhost.reset();
                break;
            case 3:
                greenGhost.reset();
                break;
        }
    }

    public void drawGhosts(Graphics2D g2) {
        // Modifier la méthode pour dessiner les fantômes en bleu quand ils sont vulnérables
        for (int i = 0; i < 4; i++) {
            int x, y;
            BufferedImage ghostImage;
            Ghost currentGhost;
            switch (i) {
                case 0:
                    currentGhost = redGhost;
                    ghostImage = isVulnerable[i] ? blueG : redG;
                    break;
                case 1:
                    currentGhost = blueGhost;
                    ghostImage = isVulnerable[i] ? blueG : blueG;
                    break;
                case 2:
                    currentGhost = purpleGhost;
                    ghostImage = isVulnerable[i] ? blueG : purpleG;
                    break;
                case 3:
                    currentGhost = greenGhost;
                    ghostImage = isVulnerable[i] ? blueG : greenG;
                    break;
                default:
                    continue;
            }

            g2.drawImage(ghostImage, currentGhost.x, currentGhost.y, ghostSize, ghostSize, null);
        }
    }

}
