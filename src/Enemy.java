import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy extends Entity{
    GamePanel gamePanel;
    Player player;
    private int velX, velY;

    public Enemy(int worldX, int worldY, int speed, int health, Player player, boolean alive) {
        super(worldX, worldY , speed, "down", 0, 0, health, alive);
        this.player = player;
    }

    public int[] getEnemyVelocity() {
        double dx = player.getPlayerX() - getScreenX();
        double dy = player.getPlayerY() - getScreenY();

        double length = Math.sqrt(dx*dx + dy*dy);

        dx /= length;
        dy /= length;

        velX = (int) (dx * super.getSpeed());
        velY = (int) (dy * super.getSpeed());
        int[] xy = new int[2];
        xy[0] = velX;
        xy[1] = velY;
        return xy;
    }
}
