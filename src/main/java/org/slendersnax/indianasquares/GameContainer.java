// Orban Abel, 621

package org.slendersnax.indianasquares;

import javax.swing.JFrame;

import org.slendersnax.indianasquares.collection.Game;
import org.slendersnax.indianasquares.collection.MainMenu;

public class GameContainer {
    private JFrame mainFrame;
    private MainMenu mainMenuPanel;
    private Game gamePanel;

    private int windowWidth = 814;
    private int windowHeight = 635;


    public GameContainer() {
        mainFrame = new JFrame();
        gamePanel = new Game(windowWidth, windowHeight);
        mainMenuPanel = new MainMenu(windowWidth, windowHeight, mainFrame, gamePanel);

        mainFrame.setTitle("Indiana Squares");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(windowWidth, windowHeight);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null); // centering the window on screen (doesn't work on multi monitor display)
        mainFrame.setContentPane(mainMenuPanel);
        mainFrame.setVisible(true);
    }
}
