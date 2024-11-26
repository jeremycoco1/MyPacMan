package Control;

import Model.Fruit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static Model.Map.mapTileNum;
import static View.SettingsDimension.tilesSize;

public class FruitManager {

    PlayerManager player;
    TileManager tm;

    private Random random = new Random();
    ArrayList<Fruit> fruits;
    private Fruit currentFruit;
    private boolean isVisible = false;
    private int posX, posY;

    private long spawnTime;
    private static final long FRUIT_DURATION = 5000; // 5 secondes
    private Rectangle solidArea;


    public FruitManager(PlayerManager player, TileManager tm) {
        this.tm = tm;
        this.player = player;
        this.fruits = new ArrayList<>();
        initializeFruitTypes();
        loadFruitImages();

        solidArea = new Rectangle(6, 6, 12, 12);
    }

    private void initializeFruitTypes() {
        // Ajouter les différents types de fruits avec leurs caractéristiques
        fruits.add(new Fruit("Cherry", "/fruits/cerise.png", 100, 0));
        fruits.add(new Fruit("Strawberry", "/fruits/fraise.png", 300, 200));
        fruits.add(new Fruit("Orange", "/fruits/orange.png", 500, 500));
        fruits.add(new Fruit("Apple", "/fruits/pomme.png", 700, 1000));
    }

    private void loadFruitImages() {
        try {
            for (Fruit fruit : fruits) {
                fruit.image = ImageIO.read(getClass().getResourceAsStream(fruit.imagePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (isVisible) {
            // Vérifier si le temps d'affichage est dépassé
            if (System.currentTimeMillis() - spawnTime > FRUIT_DURATION) {
                isVisible = false;
                return;
            }

            // Vérifier la collision avec le joueur
            if (checkCollision()) {
                collectFruit();
            }
        }
    }

    private boolean checkCollision() {
        Rectangle fruitArea = new Rectangle(posX + solidArea.x,
                posY + solidArea.y,
                solidArea.width,
                solidArea.height);

        Rectangle playerArea = new Rectangle(player.getPcPosX() + player.solidArea.x,
                player.getPcPosY() + player.solidArea.y,
                player.solidArea.width,
                player.solidArea.height);

        return fruitArea.intersects(playerArea);
    }

    private void collectFruit() {
        if (currentFruit != null) {
            player.getCoins().addScore(currentFruit.points);
        }
        isVisible = false;
    }


    public void spawn(int currentScore) {
        if (isVisible) return;

        // Sélectionner un fruit approprié basé sur le score
        ArrayList<Fruit> availableFruits = new ArrayList<>();
        for (Fruit fruit : fruits) {
            if (currentScore >= fruit.scoreThreshold) {
                availableFruits.add(fruit);
            }
        }

        if (!availableFruits.isEmpty()) {
            currentFruit = availableFruits.get(random.nextInt(availableFruits.size()));

            // Essayer de trouver une position valide
            Point validPos = findValidPosition();
            if (validPos != null) {
                posX = validPos.x;
                posY = validPos.y;
                isVisible = true;
                spawnTime = System.currentTimeMillis();
            }
        }
    }

    private Point findValidPosition() {
        int maxAttempts = 50;
        int attempt = 0;

        while (attempt < maxAttempts) {
            int col = random.nextInt(23) + 1; // Position en colonnes
            int row = random.nextInt(23) + 1; // Position en lignes

            // Vérifier si la position est valide (pas un mur)
            if (mapTileNum[col][row] == 1) {
                return new Point(col * tilesSize, row * tilesSize);
            }
            attempt++;
        }
        return null;
    }



    public void draw(Graphics2D g2) {
        if (!isVisible || currentFruit == null) return;

        g2.drawImage(currentFruit.image, posX, posY, tilesSize, tilesSize, null);

        // Optionnel : Afficher le nombre de points que vaut le fruit
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 10));
        String points = String.valueOf(currentFruit.points);
        FontMetrics fm = g2.getFontMetrics();
        int textX = posX + (tilesSize - fm.stringWidth(points)) / 2;
        int textY = posY + tilesSize + 12;
        g2.drawString(points, textX, textY);
    }

    public void reset() {
        isVisible = false;
        currentFruit = null;
    }
}

