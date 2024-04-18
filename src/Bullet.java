import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bullet extends Entity{
    private GamePanel gamePanel;
    private MouseHandler mouseHandler;
    private Player player;
    private boolean shoot;
    private int bulletX, bulletY;
    private Image bulletImage;
    private long startTime;
    private long timePassed;


    public Bullet(GamePanel gamePanel, MouseHandler mouseHandler, Player player) {
        super(-1000, -1000, 8, "down");
        shoot = false;
        timePassed = 1000000001;
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
        shoot = true;
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        bulletX = (int) b.getX() - 320;
        bulletY = (int) b.getY() - 40;
    }

    public void draw(Graphics2D g2) {
        //g2.drawImage(bulletImage, player.getPlayerX() + gamePanel.getTileSize()/2 - 2, player.getPlayerY() + gamePanel.getTileSize()/2 - 2, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
        g2.drawImage(bulletImage, bulletX, bulletY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
    }

    public boolean getShoot() {
        return shoot;
    }
}
