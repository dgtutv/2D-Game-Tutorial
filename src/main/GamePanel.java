package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {     //Runnable allows implementation of thread

    // SCREEN SETTINGS
    final int originalTileSize = 16;        //16x16 tile default size for entities/tiles etc.
    final int scale = 3;        //How much we scale the 16x16 tile size

    public final int tileSize = originalTileSize * scale;      //48x48 tile, actual size displayed on game screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;        //Screen is 16x12 tiles
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;       //Scaling our tiles to pixels, so 768 x 576 pixels

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;      //Something we can start and stop, keeps game running whether thread is started or stopped
    Player player = new Player(this, keyH);

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);       //improves game's rendering performance
        this.addKeyListener(keyH);      //instantiate our keyListener class in the game panel to accept input
        this.setFocusable(true);        //GamePanel can be focused to receive key input
    }

    public void startGameThread(){

        gameThread = new Thread(this);      //Passing gamePanel class to the Thread constructor
        gameThread.start();         //Calls the run method
    }

    @Override
    //Below is the delta method game loop
    public void run() {

        double drawInterval = 1000000000/FPS;   //We draw the screen every 0.0167 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;         //Accurately set the lastTime before the next iteration of the loop.

            if(delta > 1){      //We're essentially looping until the amount of time passed == drawInterval , then updating and repainting.
                update();
                repaint();
                delta--;        //Reset delta to 0
            }
        }
    }
    public void update(){

         player.update();
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);        //Whenever use paintComponent method, need this line, g is an entity

        Graphics2D g2 = (Graphics2D)g;      //change graphics to Graphics2D class, more functions than Graphics class, g2 is an entity

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();       //release any system resources saved for g2
    }
}
