package Model;

import Control.PlayerManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Blinky extends OldGhost {
PlayerManager pl;
    public Blinky(PlayerManager pl){
        this.pl=pl;
        pos=new Point(10*24,10*24);
        basePos=new Point(Map.mapTileNum[0].length - 6*24, 4*24);
        speed=3;
        getImage();
    }

public void getImage() {

    try {
        redG = ImageIO.read(getClass().getResourceAsStream("/ghosts/redG.png"));

    } catch (IOException e) {
        e.printStackTrace();
    }

}


    @Override
    public void chaseMode() {

            target = new Point(pl.pcPosX, pl.pcPosY);

    }

    @Override
    public void scatterMode() {
        target = basePos;
    }
public void drawBlinki(Graphics2D g2){
    g2.drawImage(redG, pos.x, pos.y, 24, 24, null);
}


}
