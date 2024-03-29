// Orban Abel, 621
package org.slendersnax.indianasquares.collection.controllers;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.io.File;

public class AudioPlayer {
    private Clip clip;
    private String status;
    private AudioInputStream audioInputStream;
    private String filePath;

    // using clips cuz there isn't any long file (also simpler)
    public AudioPlayer(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.filePath = filePath;
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public void stop() {
        clip.stop();
        clip.close();
    }

    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
    }

    // works as a play or restart
    public void play() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(0);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

}
