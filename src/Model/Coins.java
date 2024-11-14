package Model;

import View.PlayerManager;

import java.awt.*;

import static Model.Map.mapTileNum;
import static View.SettingsDimension.*;

public class Coins {

    PlayerManager pl;
    private boolean[][] coins;
    private boolean[][] bigCoins;
    public int powerModeTimer = 0;
    public static final int POWER_MODE_DURATION = 600;
    public boolean powerMode = false;
    private final int[][] BIG_COINS_POSITIONS = {
            {1, 1},
            {23, 1},
            {1, 23},
            {23, 23}
    };

    public int score = 0;
    public int highScore = 0;

    public Coins(PlayerManager playerManager) {
        this.pl = playerManager;
        coins = new boolean[maxMapCol][maxMapRow];
        this.bigCoins = new boolean[maxMapCol][maxMapRow];
        initializeCoins();
        initializeBigCoins();
    }


    private void initializeCoins() {
        for (int col = 0; col < maxMapCol; col++) {
            for (int row = 0; row < maxMapRow; row++) {
                if (mapTileNum[col][row] == 1) {
                    if (!isBigCoinsPosition(col, row)) {
                        coins[col][row] = true;
                    }
                }
            }
        }
    }

    private void initializeBigCoins() {
        for (int[] position : BIG_COINS_POSITIONS) {
            bigCoins[position[0]][position[1]] = true;
        }
    }

    private boolean isBigCoinsPosition(int col, int row) {
        for (int[] position : BIG_COINS_POSITIONS) {
            if (position[0] == col && position[1] == row) {
                return true;
            }
        }
        return false;
    }

    public boolean getCoinsAt(int col, int row) {
        if (col >= 0 && col < maxMapCol && row >= 0 && row < maxMapRow) {
            return coins[col][row];
        }
        return false;
    }

    public boolean getBigCoinsAt(int col, int row) {
        if (col >= 0 && col < maxMapCol && row >= 0 && row < maxMapRow) {
            return bigCoins[col][row];
        }
        return false;
    }

    public void addScore(int points) {
        score += points;
//        if (score > highScore) {
//            highScore = score;
//        }
    }
    public void updateHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }
    public int getHighScore() {
        return highScore;
    }
    public void removeCoins(int col, int row) {
        if (col >= 0 && col < maxMapCol && row >= 0 && row < maxMapRow) {
            coins[col][row] = false;
        }
    }

    public void removeBigCoins(int col, int row) {
        if (col >= 0 && col < maxMapCol && row >= 0 && row < maxMapRow) {
            bigCoins[col][row] = false;
        }
    }

    public void checkCoins() {
        // Calculer la position centrale de Pac-Man
        int centerX = pl.pcPosX + 9;
        int centerY = pl.pcPosY + 9;

        // Convertir en position de grille
        int col = centerX / tilesSize;
        int row = centerY / tilesSize;

        if (this.getBigCoinsAt(col, row)) {
            this.removeBigCoins(col, row);
            this.addScore(50);
           activatePowerMode(); // Active le mode power
        }

        else if (this.getCoinsAt(col, row)) {  // Vous devrez implémenter cette méthode dans votre classe Map
            this.removeCoins(col, row);    // Retirer la pastille
            this.addScore(10);               // Ajouter 10 points pour une pastille normale
        }
    }
    public void activatePowerMode() {
        powerMode = true;
        powerModeTimer = POWER_MODE_DURATION;

        pl.getGhosts().setVulnerable(true);
    }

    public void drawCoins(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        for (int col = 0; col < maxMapCol; col++) {
            for (int row = 0; row < maxMapRow; row++) {
                int x = col * tilesSize + tilesSize / 2;
                int y = row * tilesSize + tilesSize / 2;

                if (coins[col][row]) {
                    // coins normaux
                    g2.fillOval(x - 2, y - 2, 4, 4);
                }
                if (bigCoins[col][row]) {
                    // Big coins(plus grands)
                    g2.fillOval(x - 6, y - 6, 12, 12);
                }
            }
        }
    }
}


