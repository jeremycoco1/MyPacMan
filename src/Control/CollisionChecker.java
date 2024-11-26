package Control;

import Model.Entity;
import Model.Ghost;
import Model.Map;

import static View.SettingsDimension.tilesSize;

public class CollisionChecker {
    TileManager tm;
    Map map;


    public CollisionChecker(TileManager tm) {
        this.tm=tm;
        this.map = new Map();

    }
    public void checkTile(Entity entity) {
        int entityLeftX = entity.pcPosX + entity.solidArea.x;
        int entityRightX = entity.pcPosX + entity.solidArea.x + entity.solidArea.width ;
        int entityTopY = entity.pcPosY + entity.solidArea.y;
        int entityBottomY = entity.pcPosY + entity.solidArea.y + entity.solidArea.height;


        int entityLeftCol = entityLeftX / tilesSize;
        int entityRightCol = entityRightX / tilesSize;
        int entityTopRow = entityTopY / tilesSize;
        int entityBottomRow = entityBottomY / tilesSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityTopRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {

                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityBottomRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {

                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityLeftCol][entityBottomRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {

                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightX + entity.speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityBottomRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {

                    entity.collisionOn = true;
                }
                break;
        }
    }

    public boolean checkTile(Ghost ghost, int speed) {
        int entityLeftX = ghost.x;
        int entityRightX = ghost.x  ;
        int entityTopY = ghost.y ;
        int entityBottomY = ghost.y ;


        int entityLeftCol = entityLeftX / tilesSize;
        int entityRightCol = entityRightX / tilesSize;
        int entityTopRow = entityTopY / tilesSize;
        int entityBottomRow = entityBottomY / tilesSize;

        int tileNum1, tileNum2;

        switch (ghost.direction) {
            case "up":
                entityTopRow = (entityTopY - speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityTopRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {

                    ghost.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomY + speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityBottomRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {
                    ghost.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftX - speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityLeftCol][entityBottomRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {

                    ghost.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightX + speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityBottomRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {

                    ghost.collisionOn = true;
                }
                break;
        }
        return ghost.collisionOn;
    }
}

