package View;

import Control.GameControl;

import javax.swing.*;

public class WindowManager extends JFrame {

    GamePanel gp ;
    public WindowManager(GamePanel gp){
        this.gp=gp;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Pac Man");
        add(this.gp);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);



    }

}
