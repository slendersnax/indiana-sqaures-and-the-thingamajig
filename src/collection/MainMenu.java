// Orban Abel, 621
package collection;

import collection.components.SlrButton;
import collection.controllers.AudioPlayer;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MainMenu extends JPanel {
    private SlrButton playButton;
    private SlrButton quitButton;
    private JLabel title;
    private BufferedImage background;
    private AudioPlayer audioPlayer;

    public MainMenu(int width, int height, JFrame mainFrame, Game gamePanel) {
        try {
            background = ImageIO.read(new File("data/sprites/background_indiana.png"));
            audioPlayer = new AudioPlayer("data/sounds/indiana_kazoo.wav");
            audioPlayer.play();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // init
        playButton = new SlrButton("Play");
        quitButton = new SlrButton("Quit");
        title = new JLabel("Indiana Squares and the Thingamajig");

        // setting up
        setBounds(0, 0, width, height);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        title.setFont(new Font("Arial", 1, 20));

        // centering the elements horizontally
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.newGame();
                audioPlayer.stop();
                mainFrame.setContentPane(gamePanel);
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // these are basically empty spaces to make it prettier
        add(Box.createRigidArea(new Dimension(0, 140)));
        add(title);
        add(Box.createRigidArea(new Dimension(0,20)));
        add(playButton);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(quitButton);

        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
