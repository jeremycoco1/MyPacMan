import Control.*;
import View.GamePanel;
import View.WindowManager;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        KeyHandler keyH = new KeyHandler();
        TileManager tm = new TileManager();
        CollisionChecker cc = new CollisionChecker(tm);
        PlayerManager pl = new PlayerManager(keyH, cc,tm);
        GamePanel gp = new GamePanel(pl, keyH,tm);

        WindowManager window = new WindowManager(gp);
        GameControl gc = new GameControl(pl, keyH, gp);
        gc.startGameThread();
    }
}