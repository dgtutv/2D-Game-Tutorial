package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args){

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);      //Adds our game panel to be displayed in our window

        window.pack();      //allows us to see our game panel

        window.setLocationRelativeTo(null);     //Display window at the center of the screen
        window.setVisible(true);        //lets us see this window

        gamePanel.startGameThread();
    }
}
