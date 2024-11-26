package Model;

import java.awt.*;

public class Inky extends OldGhost {

    @Override
    public void chaseMode() {

        target = new Point(pl.pcPosX, pl.pcPosY);

    }

    @Override
    public void scatterMode() {
        target = new Point(5, 4);
    }
}
