// Orban Abel, 621

package org.slendersnax.indianasquares.collection.entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Square {
    public int width;
    public int x;
    public int y;
    public BufferedImage sprite;

    public int type = 0;
    public int transparency = 255; // not transparent

    public Square(int width, int x, int y, File imgsrc) throws IOException {
        this.width = width;
        this.x = x;
        this.y = y;

        sprite = ImageIO.read(imgsrc);
    }
}
