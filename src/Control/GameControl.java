package Control;

import View.GamePanel;

import javax.swing.*;


public class GameControl extends JPanel implements Runnable {
    PlayerManager pl;
    KeyHandler keyH;
    GamePanel gp;


    Thread gameThread;
    int FPS = 60;


    public GameControl(PlayerManager pl, KeyHandler keyH, GamePanel gp) {
        this.pl = pl;
        this.keyH = keyH;
        this.gp = gp;

        this.addKeyListener(keyH);
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
        }
    }


}


