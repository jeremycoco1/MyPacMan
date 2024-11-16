package View;

import Control.KeyHandler;
import Control.PlayerManager;
import Control.TileManager;

import javax.swing.*;
import java.awt.*;

import static View.SettingsDimension.*;

public class GamePanel extends JPanel {

    PlayerManager pl;
    TileManager tm;
    KeyHandler keyH;


    public GamePanel(PlayerManager pl, KeyHandler keyH, TileManager tm) {
        this.pl = pl;
        this.keyH = keyH;
        this.tm = tm;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tm.mapDraw(g2);
        pl.getCoins().drawCoins(g2);
        pl.draw(g2);
        pl.drawScore(g2);
        pl.drawLives(g2);
        pl.getGhosts().drawGhosts(g2);
        pl.getFruits().draw(g2);
        g2.dispose();
    }

}
