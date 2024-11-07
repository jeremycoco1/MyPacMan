package View;

import Control.GameControl;

import javax.swing.*;

public class WindowManager extends JFrame {
    GamePanel gp = GamePanel.getInstance();
    public WindowManager(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Pac Man");
        add(gp);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);



    }

}
