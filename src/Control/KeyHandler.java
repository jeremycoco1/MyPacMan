package Control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, rightPressed, leftPressed;
    public boolean pausePressed;
    private  static KeyHandler kInstance;


    public  static KeyHandler getInstance(){
        if(kInstance==null){
            kInstance= new KeyHandler();
        }
        return kInstance;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_ENTER) { // Détection de la touche Entrée
            pausePressed = !pausePressed; // Inverse l'état de pause
        }
        if (!pausePressed) {

            if (code == KeyEvent.VK_UP) {
                upPressed = true;
                downPressed = false;
                rightPressed = false;
                leftPressed = false;
            }
            if (code == KeyEvent.VK_DOWN) {
                downPressed = true;
                upPressed = false;
                rightPressed = false;
                leftPressed = false;
            }
            if (code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
                downPressed = false;
                upPressed = false;
                leftPressed = false;
            }
            if (code == KeyEvent.VK_LEFT) {
                leftPressed = true;
                rightPressed = false;
                downPressed = false;
                upPressed = false;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
