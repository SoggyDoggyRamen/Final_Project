import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bullet {
    private GamePanel gamePanel;
    private MouseHandler mouseHandler;
    private Player player;
    private boolean shoot;
    private int speed;
    private int bulletX;
    private int bulletY;
    private Image bulletImage;
    private static int bulletNum;


    public Bullet(GamePanel gamePanel, MouseHandler mouseHandler, Player player) {
        shoot = false;
        speed = 8;
        this.mouseHandler = mouseHandler;
        this.gamePanel = gamePanel;
        this.player = player;
        getBulletImage();
    }

    public void getBulletImage() {
        try {
            bulletImage = ImageIO.read(new File("Images/Projectiles/player_pew.png"));
        } catch (IOException e) {
            System.out.println("no work");
            throw new RuntimeException(e);
        }
    }

    public void update() {
        if (mouseHandler.mouse1Down) {
            shoot = true;
        }
        else {
            shoot = false;
        }
    }

    public void draw(Graphics2D g2) {
        if (shoot) {
            g2.drawImage(bulletImage, player.getPlayerX() + gamePanel.getTileSize()/2 - 2, player.getPlayerY() + gamePanel.getTileSize()/2 - 2, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        }
    }
}
