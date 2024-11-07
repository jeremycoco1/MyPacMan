import Control.GameControl;
import View.GamePanel;
import View.WindowManager;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        WindowManager window = new WindowManager();

        GameControl gc = new GameControl();
        gc.startGameThread();
    }
}