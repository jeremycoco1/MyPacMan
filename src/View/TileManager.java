package View;

import Model.Map;
import Model.Tiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

import static View.SettingsDimension.*;

public class TileManager {
    public Tiles[] tile;
    Map map = new Map();;

    public TileManager(){
        tile = new Tiles[20];
        getTileImage();


    }


    public void getTileImage() {
        try {
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/frameT.png"));

            tile[1] = new Tiles();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/black.png"));

            tile[2] = new Tiles();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/angleLT.png"));

            tile[3] = new Tiles();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/frame_L.png"));

            tile[4] = new Tiles();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/angleRT.png"));

            tile[5] = new Tiles();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/frame_R.png"));

            tile[6] = new Tiles();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tile/angleLB.png"));

            tile[7] = new Tiles();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tile/angleRB.png"));

            tile[8] = new Tiles();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/tile/frameB.png"));


            tile[9] = new Tiles();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/tile/blue_tile.png"));

            tile[10] = new Tiles();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/tile/in_ang.png"));

            tile[11] = new Tiles();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/tile/in_ang_B.png"));

            tile[12] = new Tiles();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/tile/in_ang_TR.png"));

            tile[13] = new Tiles();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/tile/in_ang_BR.png"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mapDraw(Graphics2D g2) {

        int mapCol = 0;
        int mapRow = 0;
        int screenRow=4;
        while (mapCol < maxMapCol && mapRow < maxMapRow){
            int tileNum=map.mapTileNum[mapCol][mapRow];

            int mapX=mapCol*tilesSize;
            int mapY=screenRow*tilesSize;

            g2.drawImage(tile[tileNum].image, mapX, mapY, tilesSize, tilesSize, null);
            mapCol++;

            if (mapCol == maxMapCol) {
                mapCol = 0;

                mapRow++;
                screenRow++;

            }
        }
    }

}
