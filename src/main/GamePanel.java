package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {     //Runnable allows implementation of thread

    // SCREEN SETTINGS
    final int originalTileSize = 16;        //16x16 tile default size for entities/tiles etc.
    final int scale = 3;        //How much we scale the 16x16 tile size

    final int tileSize = originalTileSize * scale;      //48x48 tile, actual size displayed on game screen
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;        //Screen is 16x12 tiles
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;       //Scaling our tiles to pixels, so 768 x 576 pixels

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;      //Something we can start and stop, keeps game running whether thread is started or stopped

    //Set player's default position and speed
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

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
    public void run() {     //Starting a thread calls the run method

        while(gameThread != null){      //As long as gameThread exists, repeat this process

            double drawInterval = 1000000000/FPS;   //We draw the screen every 0.0167 seconds
            double nextDrawTime = System.nanoTime() + drawInterval;     //The current time + drawInterval to get when to draw screen next

            long currentTime = System.nanoTime();       //Current system time in nanoseconds, nano is very precise

            //1 UPDATE: update information such as character positions
            update();

            //2 DRAW: draw the screen with the updated information
            repaint();      //How to call paintComponent method

            //3 SLEEP: wait until next frame to draw
            try {
                double remainingTime = nextDrawTime - System.nanoTime();    //Return how much time remaining until next draw time
                remainingTime = remainingTime/1000000;      //convert remainingTime to milliseconds

                if(remainingTime < 0){
                    remainingTime = 0;      //Make sure thread does not sleep if no time is left between frames
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;   //update nextDrawTime
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void update(){
        //change player location
        if(keyH.upPressed){
            playerY -= playerSpeed;
        }
        else if(keyH.downPressed){
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed){
            playerX -=playerSpeed;
        }
        else if(keyH.rightPressed){
            playerX +=playerSpeed;
        }
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);        //Whenever use paintComponent method, need this line, g is an entity

        Graphics2D g2 = (Graphics2D)g;      //change graphics to Graphics2D class, more functions than Graphics class, g2 is an entity

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);      //Tells the paintComponent what the entity is (x pos, y pos, width, height)

        g2.dispose();       //release any system resources saved for g2
    }
}
