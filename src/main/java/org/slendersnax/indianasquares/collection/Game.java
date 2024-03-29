// Orban Abel, 621
package org.slendersnax.indianasquares.collection;

import org.slendersnax.indianasquares.collection.controllers.Control;
import org.slendersnax.indianasquares.collection.entities.Square;
import org.slendersnax.indianasquares.collection.entities.Player;
import org.slendersnax.indianasquares.collection.entities.Level;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JPanel {
    // level
    private ArrayList<Square> entities;
    private Level levelController;
    private Player player;

    private Control controller;

    // standard values
    private final int standardWidth = 20;
    private final int levelWidth = 40;  // number of entities per row
    private final int levelHeight = 30; // per column

    // game values
    private int currentLevel = 0;
    private final int numOfLevels = 4;
    private boolean levelOver = false;
    private boolean gameEnded = false;  // used for some text to give the game some *plot*

    private final String infoText = "Indiana Squares has lost his sight after he stared too long at the Diamond Femur.\n" +
            "Luckily he can screech so loudly that he can use the echoes to form a mental image of his surroundings.\n" +
            "Help him regain his eyesight by finding the Thingamajig, a relic with vague magical powers that include\n" +
            "curing blindness.\n" +
            "Move with the arrow keys";
    private final String gameOverText = "Indiana Squares has regained his eyesight! Now he can look any danger squarely in the\n" +
            "eyes and run away much more gracefully, thanks to you!";

    public Game(int width, int height) {
        entities = new ArrayList<>();
        levelController = new Level();
        controller = new Control(this);

        setBounds(0, 0, width, height);
        setLayout(null);
        setBackground(Color.BLACK);
        setVisible(true);
    }

    // drawing the entities
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", 1, 12));
        g.setColor(new Color(150, 150, 150));
        int lineHeight = g.getFontMetrics().getHeight();

        if(currentLevel == 0) {
            int y = 200;
            for (String line : infoText.split("\n")) {
                g.drawString(line, 100, y += lineHeight);
            }
        }

        if(currentLevel == 4) {
            int y = 200;
            for (String line : gameOverText.split("\n")) {
                g.drawString(line, 100, y += lineHeight);
            }
        }

        // drawing a transparent image is annoying
        // so we're drawing a black square over every wall and changing its alpha value
        entities.stream().forEach(sq -> {
            g.drawImage(sq.sprite, sq.x, sq.y, null);
            g.setColor(new Color(0, 0, 0, sq.transparency));
            g.fillRect(sq.x, sq.y, sq.width, sq.width + 1);
        });

        // the original image is 50x50 and java is iffy about drawing images bigger or smaller than they are
        // so we're setting source bounds and destination bounds
        g.drawImage(player.sprite, player.x, player.y, player.x + player.width, player.y + player.width, 0, 0, 50, 50, null);
    }

    // initialising a level
    public void init() {
        Timer timer = new Timer();
        int begin = 0;
        int timeInterval = 1000 / 60;

        levelOver = false;

        try {
            player = new Player(10, 0, 0, new File("data/sprites/indiana.png"));
            entities = levelController.getLevelEntities(currentLevel, standardWidth, levelWidth, levelHeight, player);
            controller.init(currentLevel);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (currentLevel == 4) {
            entities.stream().forEach(sq -> sq.transparency = 0);
        }

        // calling the gameloop 60 times a second beginning right now (0 seconds)
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!levelOver) {
                    gameLoop();
                }
                else {
                    timer.cancel();
                    if(currentLevel < numOfLevels) {
                        currentLevel ++;
                        init();
                    }
                }
            }
        }, begin, timeInterval);
    }

    public void newGame() {
        currentLevel = 0;
        init();
    }

    public void gameLoop() {
        levelOver = controller.control(entities, player, currentLevel);
        repaint();
    }
}
