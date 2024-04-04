import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TileManager {
    private GamePanel gamePanel;
    private Tile[] tile;
    private int[][] map;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[1];
        getTileImages();
        getMap("Data/map_01");
    }

    public void getTileImages() {
        try {
            tile[0] = new Tile(ImageIO.read(new File("Images/Tiles/grass.png")));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getMap(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        map = new int[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String[] d = fileData.get(i).split(" ");
            for (int j = 0; j < d.length; j++) {
                map[i][j] = Integer.parseInt(d[j]);
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(tile[0].getImage(), 0, 0, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        g2.drawImage(tile[0].getImage(), 0, 0, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    public void printMap () {
        for (int[] row: map) {
            for (int col: row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
