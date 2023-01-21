// Orban Abel, 621
package collection.entities;

import java.io.File;
import java.io.IOException;

public class Thingamajig extends Square {
    public Thingamajig(int width, int x, int y, File imgsrc) throws IOException {
        super(width, x, y, imgsrc);
        this.type = 1;
    }
}
