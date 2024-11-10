package Model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static View.SettingsDimension.maxMapCol;
import static View.SettingsDimension.maxMapRow;

public class Map {

    public static int[][] mapTileNum;

    public Map() {
        mapTileNum = new int[maxMapCol][maxMapRow];
        loadMap("/map1/pac man pannel.txt");
    }




    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < maxMapCol && row < maxMapRow) {

                String line = br.readLine();
                while (col < maxMapCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == maxMapCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

}