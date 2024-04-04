import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int originalTileSize = 32;
    final int scale = 2;
    private final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 20;
    final int maxScreenRow = 15;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    //FPS
    final int FPS = 60;

    //Game tools
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();


    //Player variables
    Player player = new Player(this, keyHandler);

    //Tiles
    TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyHandler);
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
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.drawString("I LOVE YOU RYISNOW", 100, 100);
        tileManager.draw(g2);
        player.draw(g2);
    }

    public int getTileSize() {
        return tileSize;
    }
}
