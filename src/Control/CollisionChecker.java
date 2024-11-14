package Control;

import Model.Entity;
import Model.Map;
import View.TileManager;

import static View.SettingsDimension.tilesSize;

public class CollisionChecker {
    TileManager tm;
    Map map;
    private static CollisionChecker checkerInstance;

    private CollisionChecker(TileManager tm) {
        this.tm=tm;
        this.map = new Map();

    }

    public static CollisionChecker getInstance() {
        if (checkerInstance == null) {
            checkerInstance = new CollisionChecker(new TileManager());
        }
        return checkerInstance;
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
                    System.out.println("Collision up ");
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomY + entity.speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityBottomRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {
                    System.out.println("Collision down ");
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftX - entity.speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityLeftCol][entityBottomRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {
                    System.out.println("Collision left ");
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightX + entity.speed) / tilesSize;
                tileNum1 = map.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityBottomRow];
                if (tm.tile[tileNum1].collision == true || tm.tile[tileNum2].collision == true) {
                    System.out.println("Collision right ");
                    entity.collisionOn = true;
                }
                break;
        }


    }

}

