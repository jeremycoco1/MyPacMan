package Control;

import View.GamePanel;
import View.PlayerManager;

import javax.swing.*;


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
        pl.update();
    }
}


