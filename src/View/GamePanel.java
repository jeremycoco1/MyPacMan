package View;

import Control.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static View.SettingsDimension.*;

public class GamePanel extends JPanel {
    PlayerManager pl = PlayerManager.getInstance();
    TileManager tm = new TileManager();


    private static GamePanel gpInstance;
    KeyHandler keyH;

    private GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.keyH = KeyHandler.getInstance();
    }

    public static GamePanel getInstance() {
        if (gpInstance == null) {
            gpInstance = new GamePanel();
        }
        return gpInstance;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tm.mapDraw(g2);
        pl.getCoins().drawCoins(g2);
        pl.draw(g2);
        pl.drawScore(g2);
        pl.drawLives(g2);
        pl.ghosts.drawGhosts(g2);
        pl.getFruits().draw(g2);
        g2.dispose();
    }

}
