// Orban Abel, 621
package org.slendersnax.indianasquares.collection.controllers;

import org.slendersnax.indianasquares.collection.entities.Square;
import org.slendersnax.indianasquares.collection.entities.Player;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

// this handles movement, collision, etc
public class Control {
    // we don't need the arrow keys anywhere else so they do this
    // everywhere
    private final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private final int standardSpeed = 2;
    private final int maxPulseDistance = 66;
    private final int pulseSpeed = 2;
    private AudioPlayer audioPlayer;

    private boolean moveLeft  = false;
    private boolean moveUp    = false;
    private boolean moveRight = false;
    private boolean moveDown  = false;

    // separate these for collision so we can
    // slide along the walls (on an axis)
    private boolean xCollide = false;
    private boolean yCollide = false;

    // player movement speed
    private int momentumX;
    private int momentumY;

    private boolean levelOver = false;

    // a small inner class to handle setting the boolean movement indicators
    // we're using key bindings, not listeners so we need a class like this
    public class KeyControl extends AbstractAction {
        private String movement;
        private boolean newState;

        public KeyControl(String movement, boolean newState) {
            this.movement = movement;
            this.newState = newState;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
           switch(movement) {
               case "LEFT":
                   moveLeft = newState;
                   break;
               case "UP":
                   moveUp = newState;
                   break;
               case "RIGHT":
                   moveRight = newState;
                   break;
               case "DOWN":
                   moveDown = newState;
                   break;
           }
        }
    }

    public Control(JPanel gamePanel) {
        try {
            audioPlayer = new AudioPlayer("data/sounds/sonar_sound_effect.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // using key bindings
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed LEFT"),  "LEFT ON");
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed UP"),    "UP ON");
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed RIGHT"), "RIGHT ON");
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed DOWN"),  "DOWN ON");
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("released LEFT"),  "LEFT OFF");
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("released UP"),    "UP OFF");
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("released RIGHT"), "RIGHT OFF");
        gamePanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("released DOWN"),  "DOWN OFF");

        gamePanel.getActionMap().put("LEFT ON",  new KeyControl("LEFT", true));
        gamePanel.getActionMap().put("UP ON",    new KeyControl("UP", true));
        gamePanel.getActionMap().put("RIGHT ON", new KeyControl("RIGHT", true));
        gamePanel.getActionMap().put("DOWN ON",  new KeyControl("DOWN", true));
        gamePanel.getActionMap().put("LEFT OFF",  new KeyControl("LEFT", false));
        gamePanel.getActionMap().put("UP OFF",    new KeyControl("UP", false));
        gamePanel.getActionMap().put("RIGHT OFF", new KeyControl("RIGHT", false));
        gamePanel.getActionMap().put("DOWN OFF",  new KeyControl("DOWN", false));
    }

    public void init(int currentLevel) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        if(currentLevel != 4) {
            audioPlayer.play();
        }
        levelOver = false;
    }

    public boolean control(ArrayList<Square> entities, Player player, int currentLevel) {
        momentumX = 0;
        momentumY = 0;

        // movement
        if(moveLeft) {
            momentumX = -standardSpeed;
        }
        if(moveUp) {
            momentumY = -standardSpeed;
        }
        if(moveRight) {
            momentumX = standardSpeed;
        }
        if(moveDown) {
            momentumY = standardSpeed;
        }

        // on the last level he can see again yay
        if(currentLevel != 4) {
            entities.stream()
                    .filter(sq -> sq.transparency < 255)
                    .forEach(sq -> sq.transparency++);
        }

        // collision detection
        int i;
        player.x += momentumX;
        xCollide = false;
        for(i = 0; i < entities.size(); i ++) {
            if(player.collision(entities.get(i))) {
                xCollide = true;
                break;
            }
        }
        if(xCollide) {
            player.x -= momentumX;

            if(entities.get(i).type == 1) {
                levelOver = true;
            }
        }

        player.y += momentumY;
        yCollide = false;
        for(i = 0; i < entities.size(); i ++) {
            if(player.collision(entities.get(i))) {
                yCollide = true;
                break;
            }
        }
        if(yCollide) {
            player.y -= momentumY;

            if(entities.get(i).type == 1) {
                levelOver = true;
            }
        }

        // progression of the sonar pulse
        player.pulseDistance = (player.pulseDistance + pulseSpeed) % maxPulseDistance;

        // 'lighting' up those tiles that are within echo distance
        entities.stream()
                .filter(sq -> Math.abs(sq.x - player.x) <= player.pulseDistance && Math.abs(sq.y - player.y) <= player.pulseDistance)
                .forEach(sq -> sq.transparency = 0);

        if(levelOver) {
            audioPlayer.stop();
        }

        return levelOver;
    }
}
