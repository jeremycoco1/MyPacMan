package Model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static View.SettingsDimension.maxMapCol;
import static View.SettingsDimension.maxMapRow;

public class Map {

    public static int[][] mapTileNum;

    public Map() {
        mapTileNum = new int[maxMapCol][maxMapRow];
        loadMap("/map1/pac man pannel.txt");
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < maxMapCol && row < maxMapRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                while (col < maxMapCol) {
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

    public static List <Point> pathPoint() {

        List<Point> points = new ArrayList<>();
        for (int col = 0; col < maxMapCol; col++) {
            for (int row = 0; row < maxMapRow; row++) {
                if (mapTileNum[col][row] == 1) {
                    Point p = new Point(row*24, col*24);
                    points.add(p);
                }
            }
        }
return points;
    }
}