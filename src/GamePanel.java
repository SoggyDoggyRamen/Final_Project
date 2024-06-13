import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int originalTileSize = 32;
    final int scale = 2;
    private final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 20;
    final int maxScreenRow = 16;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    //FPS
    final int FPS = 60;

    //Game tools
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    MouseHandler mouseHandler = new MouseHandler();
    SoundHandler soundHandler = new SoundHandler();

    //Player
    Player player = new Player(this, keyHandler);

    //TileManager
    TileManager tileManager = new TileManager(this, player);

    //Projectiles
    BulletHandler bulletHandler = new BulletHandler(this, mouseHandler, player, 50, tileManager, soundHandler);

    //Enemies
    EnemyHandler enemyHandler = new EnemyHandler(this, player, tileManager, bulletHandler);

    //Hitbox detector
    HitboxDetector hitboxDetector = new HitboxDetector(enemyHandler.getPickledEggs(), bulletHandler.getBullets(), player, enemyHandler.getDrones());


    //lose scren
    BufferedImage loseScreen = ImageIO.read(new File("Images/Screens/img.png"));
    public GamePanel() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        //The time to start the while loop
        double drawInterval = (double) 1000000000 /FPS; //the amount of time that you wait until you draw again
        double nextDrawTime = System.nanoTime() + drawInterval; //the time from now to the next time you redraw the screen

        while (gameThread != null) {
            //update game variables
            update();

            //redraw screen
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; //change remaining time from nanoseconds to milliseconds

                if(remainingTime < 0) { //if game update and repaint took longer than allotted time
                    remainingTime = 0; //make time left to sleep equal to 0
                }
                Thread.sleep((long) remainingTime);

            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            nextDrawTime = System.nanoTime() + drawInterval; //reset the nextDrawTime to allow for the next allotted time
        }
    }

    public void update() {
        player.update();
        bulletHandler.update();
        enemyHandler.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        hitboxDetector.update();
        tileManager.draw(g2);
        bulletHandler.draw(g2);
        enemyHandler.draw(g2);
        player.draw(g2);
        if (!player.getAlive()) {
            g2.drawImage(loseScreen, 450, 100, 288, 320, null);
        }
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
