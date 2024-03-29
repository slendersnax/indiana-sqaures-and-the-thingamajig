// Orban Abel, 621
package org.slendersnax.indianasquares.collection.entities;

import java.io.IOException;
import java.io.File;

public class StairSquare extends Square {
    public StairSquare(int width, int x, int y, File imgsrc) throws IOException {
        super(width, x, y, imgsrc);
        this.type = 1;
    }
}
