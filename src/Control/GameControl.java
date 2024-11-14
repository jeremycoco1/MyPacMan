package Control;

import View.GamePanel;
import View.PlayerManager;

import javax.swing.*;
import java.awt.*;


public class GameControl extends JPanel implements Runnable {

    PlayerManager pl = PlayerManager.getInstance();

    KeyHandler keyH = KeyHandler.getInstance();

    Thread gameThread;
    int FPS = 60;
    GamePanel gp;

    public GameControl() {

        this.addKeyListener(keyH);
        this.gp = GamePanel.getInstance();
        this.gp.addKeyListener(keyH);

    }



    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override

    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();

            gp.repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if (!keyH.pausePressed) {
            pl.update();
            pl.getGhosts().updateGhosts();
//            checkCollisions();
        }
    }

//    private void checkCollisions() {
//        Rectangle pacmanArea = new Rectangle(pl.getPcPosX() + pl.solidArea.x,
//                pl.getPcPosY() + pl.solidArea.y,
//                pl.solidArea.width,
//                pl.solidArea.height);
//
//        if (!pl.isInvincible()) {
//            Rectangle ghostArea = new Rectangle();
//            ghostArea.width = pl.getGhosts().solidArea.width;
//            ghostArea.height = pl.getGhosts().solidArea.height;
//
//            checkGhostCollision(pacmanArea, ghostArea, pl.getGhosts().redGhostPosX, pl.getGhosts().redGhostPosY);
//            checkGhostCollision(pacmanArea, ghostArea, pl.getGhosts().blueGhostPosX, pl.getGhosts().blueGhostPosY);
//            checkGhostCollision(pacmanArea, ghostArea, pl.getGhosts().purpleGhostPosX, pl.getGhosts().purpleGhostPosY);
//            checkGhostCollision(pacmanArea, ghostArea, pl.getGhosts().greenGhostPosX, pl.getGhosts().greenGhostPosY);
//        }
//    }
//
//    private void checkGhostCollision(Rectangle pacmanArea, Rectangle ghostArea, int ghostX, int ghostY) {
//        ghostArea.x = ghostX + pl.getGhosts().solidArea.x;
//        ghostArea.y = ghostY + pl.getGhosts().solidArea.y;
//        if (pacmanArea.intersects(ghostArea)) {
//            pl.die();
//        }
//    }
}


