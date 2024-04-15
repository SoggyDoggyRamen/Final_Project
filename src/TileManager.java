import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TileManager {
    private GamePanel gamePanel;
    private Tile[] tiletypes;
    private int[][] map;
    private Player player;

    public TileManager(GamePanel gamePanel, Player player) {
        this.gamePanel = gamePanel;
        this.player = player;
        tiletypes = new Tile[2];
        getTileImages();
        getMap("Data/map_01");
    }

    public void getTileImages() {
        try {
            tiletypes[0] = new Tile(ImageIO.read(new File("Images/Tiles/grass.png")));
            tiletypes[1] = new Tile(ImageIO.read(new File("Images/Tiles/water.png")));

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
        int cols = fileData.get(0).split(" ").length;

        map = new int[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String[] d = fileData.get(i).split(" ");
            for (int j = 0; j < d.length; j++) {
                map[i][j] = Integer.parseInt(d[j]);
            }
        }
    }

    public void draw(Graphics2D g2) {
        int y = 0;
        int x = 0;
        int tileRow = 0;
        int tileCol = 0;
        for (int row = 0; row < map.length; row ++ ) {
            tileCol = 0;
            for (int col = 0; col < map[row].length; col ++) {
                x = tileCol * gamePanel.getTileSize() - player.getWorldX() + player.getPlayerX();
                y = tileRow * gamePanel.getTileSize() - player.getWorldY() + player.getPlayerY();
                g2.drawImage(tiletypes[map[row][col]].getImage(), x, y, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
                tileCol ++;
            }
            tileRow ++;
        }
    }
}
