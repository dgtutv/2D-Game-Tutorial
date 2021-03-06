package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH){

        this.gp=gp;
        this.keyH=keyH;

        setDefaultValues();
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaX = solidArea.x;
        solidAreaY = solidArea.y;
    }
    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){

        try{
            up1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up1.png")));
            up2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/up2.png")));
            down1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down1.png")));
            down2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/down2.png")));
            left1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left1.png")));
            left2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/left2.png")));
            right1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right1.png")));
            right2= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/right2.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            //change player location
            if(keyH.upPressed){
                direction = "up";
            }
            else if(keyH.downPressed){
                direction = "down";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }
            else{
                direction = "right";
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //If collision is false, player can move
            if(!collisionOn){
                switch(direction){
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -=speed;
                        break;
                    case "right":
                        x +=speed;
                        break;
                }
            }
        }


    }
    public void draw(Graphics2D g2){

        getPlayerImage();
        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        //image observer
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
