package Model;

import java.awt.*;

public class Pinky extends OldGhost {
    @Override
    public void chaseMode() {

        target = new Point(pl.pcPosX, pl.pcPosY);

    }

    @Override
    public void scatterMode() {
        target = basePos;
    }
}
