package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;       //booleans to tell if a given key is pressed

    //These 3 methods are necessary for KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();      //Returns the ASCII value of the key pressed

        if(code == KeyEvent.VK_W){      //If w pressed
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){      //if s pressed
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){      //if a pressed
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){      //if d pressed
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){      //If w un-pressed
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){      //if s un-pressed
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){      //if a un-pressed
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){      //if d un-pressed
            rightPressed = false;
        }
    }
}
