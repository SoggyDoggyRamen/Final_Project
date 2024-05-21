import java.awt.*;

public class PickledEggBullet extends Entity{

    private GamePanel gamepanel;
    private Player player;
    private boolean shoot;
    private TileManager tileManager;
    private int velX, velY;
    private Image bulletImage;

    public PickledEggBullet(Player player, GamePanel gamepanel, TileManager tileManager, int worldX, int worldY) {
        super(worldX, worldY, 10, "down", 0, 0, 1);

    }
}
